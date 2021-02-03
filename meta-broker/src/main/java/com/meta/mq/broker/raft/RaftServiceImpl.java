package com.meta.mq.broker.raft;

import com.alipay.sofa.jraft.Node;
import com.alipay.sofa.jraft.entity.Task;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RaftServiceImpl v0.1 2021/2/3 下午2:43 By haifeng.pang.
 * @description :
 */
public class RaftServiceImpl implements RaftService {

    private final BrokerStateMachine stateMachine;

    private final Node node;

    public RaftServiceImpl(BrokerStateMachine stateMachine, Node node) {
        this.stateMachine = stateMachine;
        this.node = node;
    }

    @Override
    public Boolean isLeader() {
        return stateMachine.isLeader();
    }

    @Override
    public String redirect() {
        return node.getLeaderId().toString();
    }

    @Override
    public void apply(Task task) {
        this.node.apply(task);
    }
}
