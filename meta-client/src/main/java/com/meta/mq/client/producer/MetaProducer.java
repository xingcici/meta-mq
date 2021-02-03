package com.meta.mq.client.producer;

import com.meta.mq.common.life.LifeCycle;
import com.meta.mq.common.message.Message;
import com.meta.mq.remote.protocol.RemoteResponse;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MetaConsumer v0.1 2021/1/27 下午6:44 By haifeng.pang.
 * @description :
 */
public interface MetaProducer extends LifeCycle {

    void send(Message message) throws Exception;
}
