package com.meta.mq.broker.message;

import com.meta.mq.common.message.Message;
import com.meta.mq.store.MessageStore;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageStoreServiceImpl v0.1 2021/2/3 下午3:10 By haifeng.pang.
 * @description :
 */
public class MessageStoreServiceImpl implements MessageStoreService {

    private MessageStore messageStore;

    public MessageStoreServiceImpl(MessageStore messageStore) {
        this.messageStore = messageStore;
    }

    @Override
    public Message read(String topic) {
        return null;
    }

    @Override
    public void write(Message message) {
        messageStore.write(message);
    }
}
