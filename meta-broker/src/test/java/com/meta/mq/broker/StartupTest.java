package com.meta.mq.broker;

import com.alibaba.fastjson.JSON;
import com.meta.mq.remote.bolt.MetaRemoteClient;
import com.meta.mq.remote.bolt.MetaRemoteClientConfig;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerStartupTest v0.1 2021/1/27 下午3:58 By haifeng.pang.
 * @description :
 */
public class StartupTest {

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    public static void main(String[] args) {
        BrokerController brokerController =  new BrokerController();
        brokerController.init();
        brokerController.prepare();
        brokerController.startup();

        //shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                brokerController.shutdown();
            }
        }));
    }

    @Test
    public void clientStartup() {
        MetaRemoteClient remoteClient;
        MetaRemoteClientConfig remoteClientConfig = new MetaRemoteClientConfig();
        remoteClientConfig.setAddress("localhost:9999");
        remoteClient = new MetaRemoteClient(remoteClientConfig);
        remoteClient.prepare();
        remoteClient.startup();
        while (true) {
            RemoteRequest remoteRequest = new RemoteRequest();
            remoteRequest.setCode(91);
            remoteRequest.setBody("im client");
            try {
                RemoteResponse remoteResponse = remoteClient.invokeSync("localhost:9999",remoteRequest);
                System.out.println("recived server " + JSON.toJSONString(remoteResponse));
            }catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(3000);
            }catch (Exception e) {
                //
            }

        }
    }
}