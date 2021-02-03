package com.meta.mq.remote;

import com.alipay.remoting.InvokeCallback;
import com.meta.mq.common.life.LifeCycle;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RemoteClient v0.1 2021/1/27 上午10:34 By haifeng.pang.
 * @description :
 */
public interface RemoteClient extends LifeCycle {

    RemoteResponse invokeSync(String address, RemoteRequest request) throws Exception;

    void invokeWithCallback(String address, RemoteRequest request, InvokeCallback invokeCallback, int timeout) throws Exception;
}
