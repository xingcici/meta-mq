package com.meta.mq.common.utils;

/**
 * @author : haifeng.pang.
 * @version 0.1 : ThreadUtil v0.1 2021/1/27 下午7:16 By haifeng.pang.
 * @description :
 */
public class ThreadUtil {

    public static void quietSleep(int time) {
        try {
            Thread.sleep(time);
        }catch (Exception e) {
            //
        }
    }
}
