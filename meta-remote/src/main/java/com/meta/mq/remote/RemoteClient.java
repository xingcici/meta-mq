package com.meta.mq.remote;

import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RemoteClient v0.1 2021/1/27 上午10:34 By haifeng.pang.
 * @description :
 */
public interface RemoteClient extends RemoteService{

    public RemoteResponse invokeSync(RemoteRequest request) throws Exception;
}
