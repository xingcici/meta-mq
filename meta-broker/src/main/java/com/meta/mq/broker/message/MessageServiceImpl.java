package com.meta.mq.broker.message;

import com.alipay.remoting.serialization.SerializerManager;
import com.alipay.sofa.jraft.Status;
import com.alipay.sofa.jraft.entity.Task;
import com.alipay.sofa.jraft.error.RaftError;
import com.meta.mq.broker.raft.BrokerClosure;
import com.meta.mq.broker.raft.MessageOperation;
import com.meta.mq.broker.raft.RaftService;
import com.meta.mq.remote.protocol.RemoteSysResponseCode;

import java.nio.ByteBuffer;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageServiceImpl v0.1 2021/2/3 下午2:03 By haifeng.pang.
 * @description :
 */
public class MessageServiceImpl implements MessageService {

    private final RaftService raftService;

    public MessageServiceImpl(RaftService raftService) {
        this.raftService = raftService;
    }

    @Override
    public void handleRead(String topic, BrokerClosure closure) {
        if (!raftService.isLeader()) {
            closure.failure(RemoteSysResponseCode.SYSTEM_ERROR, raftService.redirect());
            closure.run(new Status(RaftError.EPERM, "Not leader"));
            return;
        }

        try {
            final Task task = new Task();
            MessageOperation messageOperation = new MessageOperation(MessageOperation.READ, topic);
            task.setData(ByteBuffer.wrap(SerializerManager.getSerializer(SerializerManager.Hessian2).serialize(messageOperation)));
            task.setDone(closure);
            this.raftService.apply(task);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleSend(String message, BrokerClosure closure) {

        if (!raftService.isLeader()) {
            closure.failure(RemoteSysResponseCode.SYSTEM_ERROR, raftService.redirect());
            closure.run(new Status(RaftError.EPERM, "Not leader"));
            return;
        }

        try {
            final Task task = new Task();
            MessageOperation messageOperation = new MessageOperation(MessageOperation.WRITE, message);
            task.setData(ByteBuffer.wrap(SerializerManager.getSerializer(SerializerManager.Hessian2).serialize(messageOperation)));
            task.setDone(closure);
            this.raftService.apply(task);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
