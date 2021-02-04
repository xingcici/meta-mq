package com.meta.mq.broker;

import com.alipay.sofa.jraft.Node;
import com.alipay.sofa.jraft.conf.Configuration;
import com.meta.mq.broker.message.MessageService;
import com.meta.mq.broker.message.MessageServiceImpl;
import com.meta.mq.broker.message.MessageStoreService;
import com.meta.mq.broker.message.MessageStoreServiceImpl;
import com.meta.mq.broker.processor.MessagePullProcessor;
import com.meta.mq.broker.processor.MessageSendProcessor;
import com.meta.mq.broker.raft.BrokerStateMachine;
import com.meta.mq.broker.raft.RaftService;
import com.meta.mq.broker.raft.RaftServiceImpl;
import com.meta.mq.common.utils.ThreadUtil;
import com.meta.mq.raft.RaftStartupService;
import com.meta.mq.remote.bolt.MetaRemoteServer;
import com.meta.mq.remote.bolt.MetaRemoteServerConfig;
import com.meta.mq.store.MemMessageStore;
import com.meta.mq.store.MessageStore;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerController v0.1 2021/1/26 下午7:59 By haifeng.pang.
 * @description :
 */
public class BrokerController {

    private MetaRemoteServer metaRemoteServer;

    private Node node;

    private BrokerStateMachine brokerStateMachine;

    private MessageService messageService;

    private MessageStoreService messageStoreService;

    private MessageStore messageStore;

    private RaftService raftService;

    public void init() {

        messageStore = new MemMessageStore();

        messageStoreService = new MessageStoreServiceImpl(messageStore);

        brokerStateMachine = new BrokerStateMachine(messageStoreService);

        Configuration configuration = new Configuration();
        configuration.parse("localhost:8001,localhost:8002,localhost:8003");
        node = RaftStartupService.startup("meta-broker", "localhost:8003", brokerStateMachine, configuration);

        raftService = new RaftServiceImpl(brokerStateMachine, node);

        messageService = new MessageServiceImpl(raftService);

        MetaRemoteServerConfig metaRemoteServerConfig = new MetaRemoteServerConfig();
        metaRemoteServerConfig.addUserProcessor(new MessageSendProcessor(messageService));
        metaRemoteServerConfig.addUserProcessor(new MessagePullProcessor(messageService));
        metaRemoteServerConfig.setPort(8103);
        metaRemoteServer = new MetaRemoteServer(metaRemoteServerConfig);
    }

    public void prepare() {
        metaRemoteServer.prepare();
    }

    public void startup() {

        metaRemoteServer.startup();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("当前是否为leader : " + raftService.isLeader());
                    ThreadUtil.quietSleep(3000);
                }
            }
        });
        thread.start();
    }

    public void shutdown() {
        metaRemoteServer.shutdown();
    }
}
