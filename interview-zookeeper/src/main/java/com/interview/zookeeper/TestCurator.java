package com.interview.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 23:41
 */
public class TestCurator {
    static String zkHost = "127.0.0.1:2181";
    static int sessionTimeout = 1000;

    public static void main(String[] args) throws Exception {

        // 初始化过程中的重试机制
        ExponentialBackoffRetry exponentialBackoffRetry = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().connectString(zkHost)
                .sessionTimeoutMs(sessionTimeout).retryPolicy(exponentialBackoffRetry)
                .build();

        // 重点， 开启
        curatorFramework.start();

        curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL).forPath("/","".getBytes());

        curatorFramework.getData().forPath("");

        curatorFramework.setData().forPath("","".getBytes());

        curatorFramework.delete().deletingChildrenIfNeeded().forPath("");

        curatorFramework.checkExists().forPath("");

        curatorFramework.close();




    }
}
