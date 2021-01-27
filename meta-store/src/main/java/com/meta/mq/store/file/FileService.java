package com.meta.mq.store.file;

import com.meta.mq.common.message.Message;

/**
 * @author : haifeng.pang.
 * @version 0.1 : AbstractFile v0.1 2021/1/27 下午6:17 By haifeng.pang.
 * @description :
 */
public interface FileService {

    Message read(String topic, Long offset);

    void write(Message message);
}
