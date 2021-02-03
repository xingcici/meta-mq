package com.meta.mq.broker.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import com.alipay.sofa.jraft.Status;
import com.meta.mq.broker.message.MessageService;
import com.meta.mq.broker.raft.BrokerClosure;
import com.meta.mq.remote.protocol.MessagePullRequest;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageConsumerProcessor v0.1 2021/1/27 下午6:34 By haifeng.pang.
 * @description :
 */
public class MessagePullProcessor extends AsyncUserProcessor<MessagePullRequest> {

    private MessageService messageService;

    public MessagePullProcessor(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, MessagePullRequest request) {
        final BrokerClosure closure = new BrokerClosure() {

            @Override
            public void run(Status status) {
                asyncCtx.sendResponse(getRemoteResponse());
            }
        };
        this.messageService.handleRead(request.getBody(), closure);
    }

    @Override
    public String interest() {
        return MessagePullRequest.class.getName();
    }
}
