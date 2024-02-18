package com.interview.zookeeper.scheduletask;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 16:47
 */
public class TaskThread {
    static String zkHost = "127.0.0.1:2181";
    static int sessionTimeout = 2000;
    private ZooKeeper zooKeeper;

    private static String taskPath = "/serverTask/task";

    /**
     * 服务节点 名称
     */
    private String serverName;

    public TaskThread(String serverName) {
        this.serverName = serverName;
    }

    public void doTask() {
        String zkPath = taskPath + "/task";
        // 初始化zk连接
        try {
            initZkConnect();
            toBeMaster(zooKeeper, serverName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 递归调用
     *
     * @param zooKeeper
     * @param serverName
     */
    private void toBeMaster(ZooKeeper zooKeeper, String serverName) {
        // 进行抢占创建节点，如果创建成功，则这个任务被这个服务执行，如果节点已存在，则进行监听
        zooKeeper.create(taskPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                // 表示创建成功
                if (KeeperException.Code.OK.intValue() == i) {
                    try {
                        System.out.println(serverName + " 创建节点成功，开始执行任务");
                        runTask();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 执行完之后，删除节点,重新开始抢占
                        deleteNode(taskPath);
                        System.out.println(serverName + "服务掉线！");
                    }


                }
                // 节点已存在，则进行注册监听事件
                else if (KeeperException.Code.NODEEXISTS.intValue() == i) {
                    try {
                        zooKeeper.exists(taskPath, new Watcher() {
                            @Override
                            public void process(WatchedEvent watchedEvent) {
                                // 如果发现节点被删除，则进行抢占创建节点进行占位
                                if (watchedEvent.getType() == Event.EventType.NodeDeleted) {
                                    toBeMaster(zooKeeper, serverName);
                                }

                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println("节点创建其他异常情况！");
                }
            }
        }, "ctx_data");
    }

    /**
     * 初始化zk 连接
     *
     * @throws IOException 抛出异常
     */
    private void initZkConnect() throws Exception {
         CountDownLatch countDownLatch = new CountDownLatch(1);
        zooKeeper = new ZooKeeper(zkHost, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
        System.out.println(this.serverName + ",连接zk服务成功！");
    }

    /**
     * 删除临时节点
     *
     * @param zkPath
     */
    private void deleteNode(String zkPath) {
        try {
            zooKeeper.delete(zkPath, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 模拟执行任务
     *
     * @throws InterruptedException
     */
    private void runTask() throws InterruptedException {
        System.out.println("#############开始执行任务############");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("#############执行任务结束############");
    }
}
