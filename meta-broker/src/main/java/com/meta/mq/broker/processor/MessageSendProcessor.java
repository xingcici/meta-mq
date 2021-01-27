package com.meta.mq.broker.processor;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import com.meta.mq.common.message.Message;
import com.meta.mq.remote.protocol.MessageSendRequest;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;
import com.meta.mq.remote.protocol.RemoteSysResponseCode;
import com.meta.mq.store.file.FileService;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageProducerProcessor v0.1 2021/1/27 下午6:15 By haifeng.pang.
 * @description :
 */
public class MessageSendProcessor extends SyncUserProcessor<MessageSendRequest> {

    private FileService fileService;

    public MessageSendProcessor(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Object handleRequest(BizContext bizCtx, MessageSendRequest request) throws Exception {

        RemoteResponse remoteResponse = new RemoteResponse();
        if (null == request.getBody()) {
            remoteResponse.setCode(RemoteSysResponseCode.SYSTEM_ERROR);
            remoteResponse.setBody("消息不允许为空");
            return remoteResponse;
        }
        try {
            Message message = JSON.parseObject(request.getBody(), Message.class);
            System.out.println("收到消息 : " + JSON.toJSONString(message));
            fileService.write(message);
            remoteResponse.setCode(RemoteSysResponseCode.SUCCESS);
            return remoteResponse;
        }catch (Exception e) {
            remoteResponse.setCode(RemoteSysResponseCode.SYSTEM_ERROR);
            return remoteResponse;
        }
    }

    @Override
    public String interest() {
        return MessageSendRequest.class.getName();
    }
}
