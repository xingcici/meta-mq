package com.meta.mq.broker.raft;

import java.io.Serializable;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MessageOperation v0.1 2021/2/3 下午3:20 By haifeng.pang.
 * @description :
 */
public class MessageOperation implements Serializable {
    private static final long serialVersionUID = 412834650321869753L;

    public static final int WRITE = 1;

    public static final int READ = 2;

    private int op;

    private String data;

    public MessageOperation(int op, String data) {
        this.op = op;
        this.data = data;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
