package com.meta.mq.broker.raft;

import com.alipay.sofa.jraft.Closure;
import com.meta.mq.remote.protocol.MessageResponse;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerClosure v0.1 2021/2/3 上午11:48 By haifeng.pang.
 * @description :
 */
public abstract class BrokerClosure implements Closure {
    private RemoteResponse remoteResponse;

    private RemoteRequest remoteRequest;

    public RemoteResponse getRemoteResponse() {
        return remoteResponse;
    }

    public void setRemoteResponse(RemoteResponse remoteResponse) {
        this.remoteResponse = remoteResponse;
    }

    public RemoteRequest getRemoteRequest() {
        return remoteRequest;
    }

    public void setRemoteRequest(RemoteRequest remoteRequest) {
        this.remoteRequest = remoteRequest;
    }

    public void failure(final int code, final String body) {
        final RemoteResponse remoteResponse = new RemoteResponse();
        remoteResponse.setCode(code);
        remoteResponse.setBody(body);
        setRemoteResponse(remoteResponse);
    }

    public void success(final int code, final String body) {
        final RemoteResponse remoteResponse = new RemoteResponse();
        remoteResponse.setCode(code);
        remoteResponse.setBody(body);
        setRemoteResponse(remoteResponse);
    }
}
