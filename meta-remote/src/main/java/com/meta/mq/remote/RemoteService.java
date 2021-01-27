package com.meta.mq.remote;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RemoteService v0.1 2021/1/27 上午10:34 By haifeng.pang.
 * @description :
 */
public interface RemoteService {

    void prepare();

    void start();

    void shutdown();
}
