package com.meta.mq.broker.message;

import com.meta.mq.common.message.Message;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageStoreService v0.1 2021/2/3 下午3:09 By haifeng.pang.
 * @description :
 */
public interface MessageStoreService {

    Message read(final String topic);

    void write(final Message message);
}
