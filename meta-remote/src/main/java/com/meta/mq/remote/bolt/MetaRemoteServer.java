package com.meta.mq.remote.bolt;

import com.alipay.remoting.ConnectionEventProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.RpcClient;
import com.alipay.remoting.rpc.RpcServer;
import com.alipay.remoting.rpc.protocol.UserProcessor;
import com.meta.mq.remote.RemoteServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BoltRemoteServer v0.1 2021/1/27 下午2:29 By haifeng.pang.
 * @description :
 */
public class MetaRemoteServer implements RemoteServer {

    private static final Logger log = LoggerFactory.getLogger(MetaRemoteClient.class);

    private RpcServer server;


    private final MetaRemoteServerConfig metaRemoteServerConfig;

    public MetaRemoteServer(MetaRemoteServerConfig metaRemoteServerConfig) {
        this.metaRemoteServerConfig = metaRemoteServerConfig;
    }

    @Override
    public void prepare() {
        server = new RpcServer(metaRemoteServerConfig.getPort());
        for (ConnectionEventType type : metaRemoteServerConfig.getConnectionEventProcessors().keySet()) {
            for (ConnectionEventProcessor processor : metaRemoteServerConfig.getConnectionEventProcessors().get(type)) {
                server.addConnectionEventProcessor(type, processor);
            }
        }

        for (UserProcessor userProcessor : metaRemoteServerConfig.getUserProcessors()) {
            server.registerUserProcessor(userProcessor);
        }
    }

    @Override
    public void start() {
        server.startup();
    }

    @Override
    public void shutdown() {
        server.shutdown();
    }
}
