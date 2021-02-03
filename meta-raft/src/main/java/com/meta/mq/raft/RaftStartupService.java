package com.meta.mq.raft;

import com.alipay.sofa.jraft.JRaftUtils;
import com.alipay.sofa.jraft.Node;
import com.alipay.sofa.jraft.RaftGroupService;
import com.alipay.sofa.jraft.StateMachine;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.option.NodeOptions;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RaftService v0.1 2021/2/2 下午6:21 By haifeng.pang.
 * @description :
 */
public class RaftStartupService {

    public static Node startup(String groupId, String peerId, StateMachine stateMachine, Configuration configuration) {

        NodeOptions nodeOptions = new NodeOptions();
        nodeOptions.setInitialConf(configuration);
        nodeOptions.setLogUri("/data/logs/meta-mq-broker/"+ peerId + "/raft-log");
        nodeOptions.setRaftMetaUri("/data/logs/meta-mq-broker/"+ peerId + "/raft-meta");
        nodeOptions.setSnapshotUri("/data/logs/meta-mq-broker/"+ peerId + "/raft-snapshot");
        nodeOptions.setFsm(stateMachine);
        RaftGroupService groupService = new RaftGroupService(groupId, JRaftUtils.getPeerId(peerId), nodeOptions);
        Node node = groupService.start();
        return node;
    }
}
