package com.meta.mq.store;

import com.meta.mq.common.message.Message;
import com.meta.mq.store.commitlog.CommitLog;

/**
 * @author : haifeng.pang.
 * @version 0.1 : FileMessageStore v0.1 2021/1/28 下午8:25 By haifeng.pang.
 * @description : 文件消息存储
 */
public class FileMessageStore implements MessageStore{

    private final CommitLog commitLog;

    public FileMessageStore(CommitLog commitLog) {
        this.commitLog = commitLog;
    }

    @Override
    public Message read(String topic, Long offset) {
        return null;
    }

    @Override
    public void write(Message message) {

    }
}
