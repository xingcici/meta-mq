package com.meta.mq.broker.processor;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import com.meta.mq.common.message.Message;
import com.meta.mq.remote.protocol.MessagePullRequest;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;
import com.meta.mq.remote.protocol.RemoteSysResponseCode;
import com.meta.mq.store.file.FileService;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageConsumerProcessor v0.1 2021/1/27 下午6:34 By haifeng.pang.
 * @description :
 */
public class MessagePullProcessor extends SyncUserProcessor<MessagePullRequest> {

    private FileService fileService;

    public MessagePullProcessor(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Object handleRequest(BizContext bizCtx, MessagePullRequest request) throws Exception {
        String topic = request.getBody();
        Message message;
        RemoteResponse remoteResponse = new RemoteResponse();
        try {
            message = fileService.read(topic, null);
            remoteResponse.setBody(null == message ? null : JSON.toJSONString(message));
            return remoteResponse;
        }catch (Exception e) {
            remoteResponse.setCode(RemoteSysResponseCode.SYSTEM_ERROR);
            return remoteResponse;
        }
    }

    @Override
    public String interest() {
        return MessagePullRequest.class.getName();
    }
}
