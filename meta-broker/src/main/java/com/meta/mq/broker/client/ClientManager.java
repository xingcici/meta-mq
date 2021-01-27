package com.meta.mq.broker.client;

import com.alibaba.fastjson.JSON;
import com.meta.mq.remote.bolt.MetaRemoteClient;
import com.meta.mq.remote.bolt.MetaRemoteClientConfig;
import com.meta.mq.remote.protocol.RemoteRequest;
import com.meta.mq.remote.protocol.RemoteResponse;

/**
 * @author : haifeng.pang.
 * @version 0.1 : ClientManager v0.1 2021/1/27 上午10:28 By haifeng.pang.
 * @description :
 */
public class ClientManager {

    private static MetaRemoteClient remoteClient;

    public static void main(String[] args) {
        MetaRemoteClientConfig remoteClientConfig = new MetaRemoteClientConfig();
        remoteClientConfig.setAddress("localhost:9999");
        remoteClient = new MetaRemoteClient(remoteClientConfig);
        remoteClient.prepare();
        remoteClient.start();
        while (true) {
            RemoteRequest remoteRequest = new RemoteRequest();
            remoteRequest.setCode(91);
            remoteRequest.setBody("im client");
            try {
                RemoteResponse remoteResponse = remoteClient.invokeSync(remoteRequest);
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
