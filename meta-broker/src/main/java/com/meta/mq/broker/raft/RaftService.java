package com.meta.mq.broker.raft;

import com.alipay.sofa.jraft.entity.Task;

/**
 * @author : haifeng.pang.
 * @version 0.1 : RaftService v0.1 2021/2/3 下午2:43 By haifeng.pang.
 * @description :
 */
public interface RaftService {

    Boolean isLeader();

    String redirect();

    void apply(final Task task);
}
