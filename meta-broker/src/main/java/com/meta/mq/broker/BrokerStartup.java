package com.meta.mq.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : haifeng.pang.
 * @version 0.1 : BrokerStartup v0.1 2021/1/26 下午7:53 By haifeng.pang.
 * @description :
 */
public class BrokerStartup {
    private static final Logger logger = LoggerFactory.getLogger(BrokerStartup.class);


    public static void main(String[] args) {
        startup(createController(args));
    }

    public static void startup(BrokerController controller) {
        //config
        controller.init();
        controller.prepare();
        //init server
        controller.startup();
        //shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                controller.shutdown();
            }
        }));
        logger.info("meta-mq broker startup success");
    }

    public static BrokerController createController(String[] args) {
        return new BrokerController();
    }
}
