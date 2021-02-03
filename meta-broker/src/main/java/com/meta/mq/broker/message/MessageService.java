package com.meta.mq.broker.message;

import com.meta.mq.broker.raft.BrokerClosure;
import com.meta.mq.common.message.Message;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageService v0.1 2021/2/3 下午2:01 By haifeng.pang.
 * @description :
 */
public interface MessageService {

    void handleRead (final String topic, final BrokerClosure brokerClosure);

    void handleSend (final String message, final BrokerClosure brokerClosure);
}
