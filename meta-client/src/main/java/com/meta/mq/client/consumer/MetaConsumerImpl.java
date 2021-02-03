package com.meta.mq.client.consumer;

import com.alibaba.fastjson.JSON;
import com.meta.mq.common.enums.ConsumeStatus;
import com.meta.mq.common.exception.MetaBizException;
import com.meta.mq.common.message.Message;
import com.meta.mq.common.utils.ThreadUtil;
import com.meta.mq.remote.bolt.MetaRemoteClient;
import com.meta.mq.remote.bolt.MetaRemoteClientConfig;
import com.meta.mq.remote.protocol.*;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MetaConsumerImpl v0.1 2021/1/27 下午6:47 By haifeng.pang.
 * @description :
 */
public abstract class MetaConsumerImpl implements MetaConsumer {

    private MetaRemoteClient metaRemoteClient;

    @Override
    public abstract ConsumeStatus on(Message message);

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
        while (true) {
            try {
                RemoteResponse remoteResponse = metaRemoteClient.invokeSync("localhost:8101", new MessagePullRequest("test_topic"));
                if (RemoteSysResponseCode.SUCCESS == remoteResponse.getCode()) {
                    if (null == remoteResponse.getBody()) {
                        System.out.println("拉取消息为空 继续循环");
                        ThreadUtil.quietSleep(500);
                        continue;
                    }else {
                        this.on(JSON.parseObject(remoteResponse.getBody(), Message.class));
                    }
                }else {
                    throw new MetaBizException(remoteResponse.getBody());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
