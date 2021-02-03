## Meta MQ

#### 去中心化消息队列

```
meta-broker 服务端
meta-common 公共组件
meta-client 客户端
meta-raft   共识协议
meta-remote 网络
meta-store  存储
```
部分参考 Rocket MQ。

目前是最简单版本的MQ，消息存放于内存中。不过最基础的功能都已经有了。rpc 用的是 sofa-bolt。

使用方式 
```
producer

public void producer() {
        MetaProducerImpl producer = new MetaProducerImpl();
        producer.prepare();
        producer.startup();
        while (true) {
            Message message = new Message("test_topic", IdUtil.simpleUUID(), "这是 producer 发出的消息");
            try {
                RemoteResponse remoteResponse = producer.send(message);
                System.out.println("发送结果 :" + JSON.toJSONString(remoteResponse));
            }catch (Exception e) {
                e.printStackTrace();
            }
            ThreadUtil.quietSleep(3000);
        }
    }
```

```
consumer

public class MetaConsumerV1 extends MetaConsumerImpl {
    @Override
    public ConsumeStatus on(Message message) {
        System.out.println("消费消息 : " + JSON.toJSONString(message));
        return ConsumeStatus.SUCCESS;
    }
}

public void consumer() {
        MetaConsumerV1 metaConsumerV1 = new MetaConsumerV1();
        metaConsumerV1.prepare();
        metaConsumerV1.startup();
}


```
 