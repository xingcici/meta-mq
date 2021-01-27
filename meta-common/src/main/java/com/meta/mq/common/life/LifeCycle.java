package com.meta.mq.common.life;

/**
 * @author : haifeng.pang.
 * @version 0.1 : LifeCycle v0.1 2021/1/27 下午5:31 By haifeng.pang.
 * @description :
 */
public interface LifeCycle {

    void prepare();

    void startup();

    void shutdown();

    boolean isStarted();
}
