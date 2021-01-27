package com.meta.mq.broker.processor;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.SyncUserProcessor;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerProcessor v0.1 2021/1/27 下午3:26 By haifeng.pang.
 * @description :
 */
public class BrokerProcessor extends SyncUserProcessor<RemoteRequest> {

    private static final Logger logger = LoggerFactory.getLogger(BrokerProcessor.class);

    private AtomicInteger invokeTimes    = new AtomicInteger();

    @Override
    public Object handleRequest(BizContext bizCtx, RemoteRequest request) throws Exception {
        System.out.println("broker received : " + JSON.toJSONString(request));
        RemoteResponse remoteResponse = new RemoteResponse();
        remoteResponse.setCode(1);
        remoteResponse.setBody("im server");

        return remoteResponse;
    }

    @Override
    public String interest() {
        return RemoteRequest.class.getName();
    }

    private void processTimes(RemoteRequest req) {

    }
}
