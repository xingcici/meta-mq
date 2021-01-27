package com.meta.mq.client.producer;

import com.alibaba.fastjson.JSON;
import com.meta.mq.common.message.Message;
import com.meta.mq.remote.bolt.MetaRemoteClient;
import com.meta.mq.remote.bolt.MetaRemoteClientConfig;
import com.meta.mq.remote.protocol.MessageSendRequest;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MetaProducerImpl v0.1 2021/1/27 下午6:48 By haifeng.pang.
 * @description :
 */
public class MetaProducerImpl implements MetaProducer {

    private MetaRemoteClient metaRemoteClient;

    @Override
    public RemoteResponse send(Message message) throws Exception{
        return metaRemoteClient.invokeSync(new MessageSendRequest(message));
    }

    @Override
    public void prepare() {
        MetaRemoteClientConfig remoteClientConfig = new MetaRemoteClientConfig();
        remoteClientConfig.setAddress("localhost:9999");
        metaRemoteClient = new MetaRemoteClient(remoteClientConfig);
        metaRemoteClient.prepare();
    }

    @Override
    public void startup() {
        metaRemoteClient.startup();
    }

    @Override
    public void shutdown() {
        metaRemoteClient.shutdown();
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
