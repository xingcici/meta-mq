package com.meta.mq.broker;

import com.meta.mq.broker.processor.MessagePullProcessor;
import com.meta.mq.broker.processor.MessageSendProcessor;
import com.meta.mq.remote.bolt.MetaRemoteServer;
import com.meta.mq.remote.bolt.MetaRemoteServerConfig;
import com.meta.mq.store.file.MemFileServiceImpl;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerController v0.1 2021/1/26 下午7:59 By haifeng.pang.
 * @description :
 */
public class BrokerController {

    private MetaRemoteServer metaRemoteServer;

    public void init() {
        MetaRemoteServerConfig metaRemoteServerConfig = new MetaRemoteServerConfig();
        metaRemoteServerConfig.addUserProcessor(new MessageSendProcessor(new MemFileServiceImpl()));
        metaRemoteServerConfig.addUserProcessor(new MessagePullProcessor(new MemFileServiceImpl()));
        metaRemoteServerConfig.setPort(9999);
        metaRemoteServer = new MetaRemoteServer(metaRemoteServerConfig);
    }

    public void prepare() {
        metaRemoteServer.prepare();
    }

    public void startup() {
        metaRemoteServer.startup();
    }

    public void shutdown() {
        metaRemoteServer.shutdown();
    }
}
