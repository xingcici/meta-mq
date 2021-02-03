package com.meta.mq.broker.raft;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.exception.CodecException;
import com.alipay.remoting.serialization.SerializerManager;
import com.alipay.sofa.jraft.Iterator;
import com.alipay.sofa.jraft.Status;
import com.alipay.sofa.jraft.core.StateMachineAdapter;
import com.alipay.sofa.jraft.error.RaftException;
import com.meta.mq.broker.message.MessageStoreService;
import com.meta.mq.common.message.Message;
import com.meta.mq.remote.protocol.RemoteSysResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerStateMachine v0.1 2021/2/3 上午11:45 By haifeng.pang.
 * @description :
 */
public class BrokerStateMachine extends StateMachineAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BrokerStateMachine.class);

    private MessageStoreService messageStoreService;

    public BrokerStateMachine(MessageStoreService messageStoreService) {
        this.messageStoreService = messageStoreService;
    }

    /**
     * Leader term
     */
    private final AtomicLong leaderTerm = new AtomicLong(-1);

    public boolean isLeader() {
        return this.leaderTerm.get() > 0;
    }

    @Override
    public void onApply(Iterator iter) {
        while (iter.hasNext()) {

            MessageOperation operation = null;

            BrokerClosure closure = null;

            String body = "";

            if (iter.done() != null) {
                closure = (BrokerClosure) iter.done();
            }else {
                final ByteBuffer data = iter.getData();
                try {
                    operation = SerializerManager.getSerializer(SerializerManager.Hessian2).deserialize(data.array(), MessageOperation.class.getName());
                }catch (CodecException e) {
                    logger.error("Fail to decode IncrementAndGetRequest", e);
                }
            }

            if (null != operation) {
                switch (operation.getOp()){
                    case MessageOperation.WRITE:
                        messageStoreService.write(JSON.parseObject(operation.getData(), Message.class));
                        break;
                    case MessageOperation.READ:
                        Message message = messageStoreService.read(operation.getData());
                        body = null == message ? "" : JSON.toJSONString(message);
                        break;
                }
            }

            if (null != closure) {
                closure.success(RemoteSysResponseCode.SUCCESS, body);
                closure.run(Status.OK());
            }

            iter.next();
        }

    }

    @Override
    public void onError(final RaftException e) {
        logger.error("Raft error: {}", e, e);
    }

    @Override
    public void onLeaderStart(final long term) {
        this.leaderTerm.set(term);
        super.onLeaderStart(term);

    }

    @Override
    public void onLeaderStop(final Status status) {
        this.leaderTerm.set(-1);
        super.onLeaderStop(status);
    }
}
