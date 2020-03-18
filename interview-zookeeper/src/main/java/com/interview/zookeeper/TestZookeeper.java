package com.interview.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * zk 原生客户端
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-07 22:30
 */
public class TestZookeeper {
    static String zkHost = "127.0.0.1:2181";
    static int sessionTimeout = 2000;
    static ZooKeeper zooKeeper;


    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(zkHost, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> childrenList = null;
                try {
                    childrenList = zooKeeper.getChildren("/", false);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (String str : childrenList) {
                    System.out.println("node is -->" + str);
                }

                System.out.println("*************************");
            }
        });
    }

    @Test
    public void createNode() throws KeeperException, InterruptedException {
        String s = zooKeeper.create("/study", "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

        System.out.println("result:" + s);
    }

    public void deleteNode() {
        // zooKeeper.delete();
    }

    @Test
    public void getNodeDataAndWatch() throws KeeperException, InterruptedException {
        List<String> childrenList = zooKeeper.getChildren("/", true);

        for (String str : childrenList) {
            System.out.println("node is -->" + str);
        }

    }

    @Test
    public void isExistNode() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/study", false);

        if (null == exists) {
            System.out.println("no exist");
        } else {
            System.out.println(" exist");

        }

    }
}
