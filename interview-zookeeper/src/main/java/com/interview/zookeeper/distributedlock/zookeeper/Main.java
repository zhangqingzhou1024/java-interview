package com.interview.zookeeper.distributedlock.zookeeper;

import java.util.concurrent.TimeUnit;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 22:34
 */
public class Main {
    public static void main(String[] args) throws Exception {



        Thread userPayOrder = new Thread(() -> new UserPayOrder().startPayOrder(1L));
        Thread timeOutOrderJob = new Thread(() -> new TimeOutOrderJob().updateUserOrderState());
        // 1。 用户付款在前，调度任务在后
       /* userPayOrder.start();
        TimeUnit.SECONDS.sleep(1);
        timeOutOrderJob.start();*/

        // 2。 调度任务在前，用户付款在后

        timeOutOrderJob.start();
        TimeUnit.SECONDS.sleep(1);
        userPayOrder.start();


      TimeUnit.SECONDS.sleep(18);
    }
}
