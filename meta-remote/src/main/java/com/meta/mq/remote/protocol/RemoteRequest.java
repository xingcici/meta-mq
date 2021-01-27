package com.meta.mq.remote.protocol;

import java.io.Serializable;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RemoteCommand v0.1 2021/1/27 上午10:38 By haifeng.pang.
 * @description :
 */
public class RemoteRequest implements Serializable {

    private int code;

    private int version;

    private String body;

    public RemoteRequest() {
    }

    public RemoteRequest(int code, int version, String body) {
        this.code = code;
        this.version = version;
        this.body = body;
    }

    public RemoteRequest(Builder builder) {
        this.code = builder.code;
        this.version = builder.version;
        this.body = builder.body;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static class Builder {
        private int code;

        private int version;

        private String body;

        public Builder() {
        }
        public Builder code (int code) {
            this.code = code;
            return this;
        }
        public Builder version(int version) {
            this.version = version;
            return this;
        }
        public Builder body(String body) {
            this.body = body;
            return this;
        }
        public RemoteRequest build() {
            return new RemoteRequest(this);
        }
    }
}
