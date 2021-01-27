package com.meta.mq.remote.bolt;

import com.alipay.remoting.ConnectionEventProcessor;
import com.alipay.remoting.ConnectionEventType;
import com.alipay.remoting.rpc.protocol.UserProcessor;

import java.util.*;

/**
 * @author : haifeng.pang.
 * @version 0.1 : MetaRemoteServerConfig v0.1 2021/1/27 下午2:35 By haifeng.pang.
 * @description :
 */
public class MetaRemoteServerConfig extends AbstractRemoteConfig{

    private int port;

    private Map<ConnectionEventType, List<ConnectionEventProcessor>> connectionEventProcessors = new HashMap<>();

    private Set<UserProcessor> userProcessors = new HashSet<>();

    public MetaRemoteServerConfig() {
    }

    public void addConnectionEventProcessor(ConnectionEventType connectionEventType, ConnectionEventProcessor connectionEventProcessor) {
        List<ConnectionEventProcessor> processorList = this.connectionEventProcessors.get(connectionEventType);
        if (processorList == null) {
            this.connectionEventProcessors.putIfAbsent(connectionEventType, new ArrayList<ConnectionEventProcessor>(1));
            processorList = this.connectionEventProcessors.get(connectionEventType);
        }
        processorList.add(connectionEventProcessor);
    }

    public void addUserProcessor(UserProcessor userProcessor) {
        userProcessors.add(userProcessor);
    }

    public Map<ConnectionEventType, List<ConnectionEventProcessor>> getConnectionEventProcessors() {
        return connectionEventProcessors;
    }

    public Set<UserProcessor> getUserProcessors() {
        return userProcessors;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setConnectionEventProcessors(Map<ConnectionEventType, List<ConnectionEventProcessor>> connectionEventProcessors) {
        this.connectionEventProcessors = connectionEventProcessors;
    }

    public void setUserProcessors(Set<UserProcessor> userProcessors) {
        this.userProcessors = userProcessors;
    }
}
