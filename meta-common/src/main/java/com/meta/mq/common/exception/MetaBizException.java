package com.meta.mq.common.exception;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MetaBizException v0.1 2021/1/27 下午5:36 By haifeng.pang.
 * @description :
 */
public class MetaBizException extends RuntimeException {
    private static final long serialVersionUID = -3725740014484854032L;

    public MetaBizException(String message) {
        super(message);
    }
}
