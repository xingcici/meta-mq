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
}
