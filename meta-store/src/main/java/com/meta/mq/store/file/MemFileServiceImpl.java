package com.meta.mq.store.file;

import com.meta.mq.common.exception.MetaBizException;
import com.meta.mq.common.message.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MemFileService v0.1 2021/1/27 下午6:20 By haifeng.pang.
 * @description :
 */
public class MemFileServiceImpl implements FileService {

    private static final Map<String , BlockingQueue<Message>> MESSAGE_STORE = new HashMap<>();

    private static final  ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    @Override
    public Message read(String topic, Long offset) {
        BlockingQueue<Message> messages = MESSAGE_STORE.get(topic);
        if (null == messages) {
            return null;
        }
        return messages.poll();
    }

    @Override
    public void write(Message message) {
        try {
            rwlock.writeLock().lock();
            BlockingQueue<Message> messages = MESSAGE_STORE.get(message.getTopic());
            if (null == messages) {
                BlockingQueue<Message> newQueue = new LinkedBlockingDeque<>();
                newQueue.offer(message);
                MESSAGE_STORE.put(message.getTopic(), newQueue);
            }else {
                messages.offer(message);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new MetaBizException("写入异常");
        } finally {
            rwlock.writeLock().unlock();
        }
    }
}
