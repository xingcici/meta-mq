package com.meta.mq.broker.processor;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AsyncUserProcessor;
import com.alipay.sofa.jraft.Status;
import com.meta.mq.broker.message.MessageService;
import com.meta.mq.broker.raft.BrokerClosure;
import com.meta.mq.remote.protocol.MessageSendRequest;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageProducerProcessor v0.1 2021/1/27 下午6:15 By haifeng.pang.
 * @description :
 */
public class MessageSendProcessor extends AsyncUserProcessor<MessageSendRequest> {

    private MessageService messageService;

    public MessageSendProcessor(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handleRequest(BizContext bizCtx, AsyncContext asyncCtx, MessageSendRequest request) {
        final BrokerClosure closure = new BrokerClosure() {
            @Override
            public void run(Status status) {
                asyncCtx.sendResponse(getRemoteResponse());
            }
        };

        this.messageService.handleSend(
                request.getBody(), closure);
    }

    @Override
    public String interest() {
        return MessageSendRequest.class.getName();
    }
}
