package com.interview.zookeeper.distributedlock.zookeeper;

import org.apache.zookeeper.*;

/**
 * 实现分布式锁
 * 场景：
 * 用户付款行为 与 30分钟未付款调度任务之间的 竞争
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 22:00
 */
public class UserPayOrder {
    /**
     * 目录前缀
     */
    private static String timeOutOrderPrefix = "/timeOutOrderLock";


    public void startPayOrder(Long orderId){
        // 获取zookeeper连接
        ZooKeeper zkConnect = null;
        try {
            zkConnect = ZkConnectUtil.getZkConnect();
            doPayOrder(zkConnect, orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 开始付款操作
     *
     * @param zkConnect
     * @param orderId   订单ID
     * @return
     * @throws Exception
     */
    private String doPayOrder(ZooKeeper zkConnect, Long orderId) throws Exception {

        // 创建临时节点，用来"占位"
        zkConnect.create(timeOutOrderPrefix + "/" + orderId, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                if (i == KeeperException.Code.OK.intValue()) {
                    System.out.println("订单ID" + orderId + "，节点创建成功，用户成功付款！");

                } else if (i == KeeperException.Code.NODEEXISTS.intValue()) {
                    System.out.println("订单ID" + orderId + "，付款失败，服务繁忙，请重拾。");
                } else {
                    System.out.println("其他状态，暂不处理");
                }
            }

            ;
        }, "ctx_data");

        return null;
    }


}
