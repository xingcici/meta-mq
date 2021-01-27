package com.meta.mq.common.exception;

/**
 * @author : haifeng.pang.
 * @version 0.1 : LifeCycleException v0.1 2021/1/27 下午5:35 By haifeng.pang.
 * @description :
 */
public class LifeCycleException extends RuntimeException{

    private static final long serialVersionUID = -4171366196377778510L;

    public LifeCycleException(String message) {
        super(message);
    }
}
