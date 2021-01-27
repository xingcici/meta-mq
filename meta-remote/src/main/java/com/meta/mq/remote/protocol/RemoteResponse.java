package com.meta.mq.remote.protocol;

import java.io.Serializable;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RemoteResponse v0.1 2021/1/27 下午3:04 By haifeng.pang.
 * @description :
 */
public class RemoteResponse implements Serializable {

    private int code;

    private int version;

    private String body;

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
