package com.meta.mq.remote.protocol;

import com.alibaba.fastjson.JSON;
import com.meta.mq.common.message.Message;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageSendRequest v0.1 2021/1/27 下午7:37 By haifeng.pang.
 * @description :
 */
public class MessageSendRequest extends  RemoteRequest{
    public MessageSendRequest(Message message) {
        super(RequestCode.SEND_MESSAGE, 1, JSON.toJSONString(message));
    }
}
