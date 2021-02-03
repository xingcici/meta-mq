package com.meta.mq.client.producer;

import com.alibaba.fastjson.JSON;
import com.alipay.remoting.InvokeCallback;
import com.alipay.sofa.jraft.RouteTable;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.option.CliOptions;
import com.alipay.sofa.jraft.rpc.impl.cli.CliClientServiceImpl;
import com.meta.mq.common.message.Message;
import com.meta.mq.remote.bolt.MetaRemoteClient;
import com.meta.mq.remote.bolt.MetaRemoteClientConfig;
import com.meta.mq.remote.protocol.MessageSendRequest;
import com.meta.mq.remote.protocol.RemoteResponse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MetaProducerImpl v0.1 2021/1/27 下午6:48 By haifeng.pang.
 * @description :
 */
public class MetaProducerImpl implements MetaProducer {

    private MetaRemoteClient metaRemoteClient;

    private String addressConfig;

    private Configuration configuration;

    private String groupId;

    private CliClientServiceImpl cliClientService;

    private Executor executor = Executors.newFixedThreadPool(16);

    public MetaProducerImpl(String groupId, String addressConfig) {
        this.groupId = groupId;
        this.addressConfig = addressConfig;
    }

    @Override
    public void send(Message message) throws Exception{
        if (!RouteTable.getInstance().refreshLeader(cliClientService, groupId, 1000).isOk()) {
            throw new IllegalStateException("Refresh leader failed");
        }
        final PeerId leader = RouteTable.getInstance().selectLeader(groupId);
        System.out.println("Leader is " + leader);
        final CountDownLatch latch = new CountDownLatch(1);
        metaRemoteClient.invokeWithCallback(leader.getIp() + ":810" + leader.getEndpoint().toString().substring(13), new MessageSendRequest(message), new InvokeCallback() {
            @Override
            public void onResponse(Object result) {
                System.out.println("metaRemoteClient invokeWithCallback " + JSON.toJSONString(result));
                latch.countDown();
            }

            @Override
            public void onException(Throwable e) {
                e.printStackTrace();
                latch.countDown();
            }

            @Override
            public Executor getExecutor() {
                return executor;
            }
        }, 1000);
        latch.await();
    }

    @Override
    public void prepare() {
        MetaRemoteClientConfig remoteClientConfig = new MetaRemoteClientConfig();
        metaRemoteClient = new MetaRemoteClient(remoteClientConfig);
        metaRemoteClient.prepare();
        configuration = new Configuration();
        if (!configuration.parse(addressConfig)) {
            throw new IllegalArgumentException("Fail to parse conf:" + addressConfig);
        }
        RouteTable.getInstance().updateConfiguration(groupId, configuration);
        cliClientService = new CliClientServiceImpl();
        cliClientService.init(new CliOptions());
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
