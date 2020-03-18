package com.interview.zookeeper.distributedlock.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;

/**
 * 对于Curator而言，为了解决重复Watch的问题，它引入了一种全新的思想：Cache与ZK SERVER比对的机制。不论是原生的API，还是基于ZKCLIENT的，其实它们解决思路都是重复注册！
 * <p>
 * 思路决定出路！Curator通过事件驱动将客户端的Cache与ZK SERVER的数据比对，就自然而然的解决了重复WATCH的功能！为什么Curator能成为Apache的顶级项目呢，我想大概就是因为它的与众不同的设计思想！
 * <p>
 * 在Curator中，有2种Listener，一个是监控节点的NodeCacheListener，一个是监控子节点的PathChildrenCacheListener。PathChildernCacheListener可以监控子节点的新增、修改、删除，非常好用！
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-09 00:29
 */
public class CuratorWatch {
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

        ExecutorService executorService = newFixedThreadPool(10);
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                CuratorEventType type = curatorEvent.getType();
                String path = curatorEvent.getPath();

            }
        }, executorService).forPath("/", "".getBytes());

        NodeCache nodeCache = new NodeCache(curatorFramework, "", false);

        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                //nodeCache.
            }
        }, executorService);


    }
}
