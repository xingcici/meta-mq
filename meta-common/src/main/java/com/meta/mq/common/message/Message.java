package com.meta.mq.common.message;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : haifeng.pang.
 * @version 0.1 : Message v0.1 2021/1/27 下午6:02 By haifeng.pang.
 * @description :
 */
public class Message extends AbstractMessage {

    private String topic;

    private long offset;

    private String messageId;

    private String body;

    private Map<String, String> properties = new HashMap<>();

    public Message() {
    }

    public Message(String topic, String messageId, String body) {
        this.topic = topic;
        this.messageId = messageId;
        this.body = body;
    }

    public void putProperty(String key, String value) {
        this.properties.put(key, value);
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
