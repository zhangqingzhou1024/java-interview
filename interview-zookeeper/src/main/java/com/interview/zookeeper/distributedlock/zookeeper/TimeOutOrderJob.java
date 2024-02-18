package com.interview.zookeeper.distributedlock.zookeeper;

import org.apache.zookeeper.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 22:16
 */
public class TimeOutOrderJob {
    /**
     * 目录前缀
     */
    private static String timeOutOrderPrefix = "/timeOutOrderLock";

    /**
     * 执行调度任务
     *
     * @throws Exception
     */
    public void updateUserOrderState() {
        // 获取未付款订单列表
        List<Order> orderList = getAllLast30MinOrderList();
        ZooKeeper zkConnect = ZkConnectUtil.getZkConnect();

        Iterator<Order> iterator = orderList.iterator();
        while (iterator.hasNext()) {
            Order order = iterator.next();
            Long id = order.getId();

            String zkPath = timeOutOrderPrefix + "/" + id;

            zkConnect.create(zkPath, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
                @Override
                public void processResult(int i, String s, Object o, String s1) {
                    if (i == KeeperException.Code.OK.intValue()) {
                        // 执行调度任务
                        System.out.println("订单ID" + id + ",30分钟未处理，则进行处理。");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            zkConnect.delete(zkPath, -1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (i == KeeperException.Code.NODEEXISTS.intValue()) {
                        // 如果节点已存在，则直接跳过，不进行处理
                        System.out.println("订单ID" + id + ",当前节点已被创建，则直接跳过。");
                        iterator.remove();
                    } else {
                        System.out.println("其他情况暂不处理！");
                    }

                }
            }, "ctx_data");

        }

    }


    /**
     * mock 数据
     *
     * @return 模拟查询数据库，获取最近30分钟未付款的订单列表
     */
    private List<Order> getAllLast30MinOrderList() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, "NO PAY"));
        orders.add(new Order(2L, "NO PAY"));

        return orders;
    }


    /**
     * 订单类
     */
    private class Order {
        private Long id;
        private String state;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Order(Long id, String state) {
            this.id = id;
            this.state = state;
        }
    }

}
