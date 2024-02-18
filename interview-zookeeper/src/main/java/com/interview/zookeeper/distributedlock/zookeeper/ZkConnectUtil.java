package com.interview.zookeeper.distributedlock.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 22:23
 */
public class ZkConnectUtil {
    static String zkHost = "127.0.0.1:2181";
    static int sessionTimeout = 1000;

    /**
     * 初始化zk 连接
     *
     * @throws IOException 抛出异常
     */
    public static ZooKeeper getZkConnect() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(zkHost, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("连接zk服务成功！");
        return zooKeeper;
    }
}
