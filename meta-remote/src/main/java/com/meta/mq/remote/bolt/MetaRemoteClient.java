package com.meta.mq.remote.bolt;

import com.alipay.remoting.ConnectionEventProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcClient;
import com.alipay.remoting.rpc.protocol.UserProcessor;
import com.meta.mq.remote.RemoteClient;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BoltRemoteClient v0.1 2021/1/27 下午2:29 By haifeng.pang.
 * @description :
 */
public class MetaRemoteClient implements RemoteClient {

    private static final Logger log = LoggerFactory.getLogger(MetaRemoteClient.class);

    private final RpcClient client = new RpcClient();

    private String address;

    private final MetaRemoteClientConfig metaRemoteClientConfig;

    public MetaRemoteClient(MetaRemoteClientConfig metaRemoteClientConfig) {
        this.metaRemoteClientConfig = metaRemoteClientConfig;
    }

    @Override
    public void prepare() {
        for (ConnectionEventType type : metaRemoteClientConfig.getConnectionEventProcessors().keySet()) {
            for (ConnectionEventProcessor processor : metaRemoteClientConfig.getConnectionEventProcessors().get(type)) {
                client.addConnectionEventProcessor(type, processor);
            }
        }

        for (UserProcessor userProcessor : metaRemoteClientConfig.getUserProcessors()) {
            client.registerUserProcessor(userProcessor);
        }

        this.address = metaRemoteClientConfig.getAddress();
    }

    @Override
    public void start() {
        client.startup();
    }

    @Override
    public void shutdown() {
        client.shutdown();
    }

    @Override
    public RemoteResponse invokeSync(RemoteRequest request) throws Exception{
        return  (RemoteResponse) client.invokeSync(address, request, 1000);
    }
}
