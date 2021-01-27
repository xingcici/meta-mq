package com.meta.mq.remote.protocol;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageSendRequest v0.1 2021/1/27 下午7:37 By haifeng.pang.
 * @description :
 */
public class MessagePullRequest extends  RemoteRequest{

    public MessagePullRequest(String topic) {
        super(RequestCode.PULL_MESSAGE, 1, topic);
    }
}
