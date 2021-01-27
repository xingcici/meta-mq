package com.meta.mq.broker;

import com.meta.mq.broker.processor.BrokerProcessor;
import com.meta.mq.remote.bolt.MetaRemoteServer;
import com.meta.mq.remote.bolt.MetaRemoteServerConfig;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerController v0.1 2021/1/26 下午7:59 By haifeng.pang.
 * @description :
 */
public class BrokerController {

    private MetaRemoteServer metaRemoteServer;


    public Boolean init() {
        MetaRemoteServerConfig metaRemoteServerConfig = new MetaRemoteServerConfig();
        metaRemoteServerConfig.addUserProcessor(new BrokerProcessor());
        metaRemoteServerConfig.setPort(9999);
        metaRemoteServer = new MetaRemoteServer(metaRemoteServerConfig);
        return true;
    }

    public Boolean prepare() {
        metaRemoteServer.prepare();
        return true;
    }

    public Boolean startup() {
        metaRemoteServer.start();
        return true;
    }

    public Boolean shutdown() {
        metaRemoteServer.shutdown();
        return true;
    }
}
