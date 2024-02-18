package com.interview.zookeeper.distributedlock.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 23:56
 */
public class ZkClientWatch {
    public static void main(String[] args) throws Exception {
        // 获取连接
        ZkConnection zkConnection = new ZkConnection("127.0.0.1:2181", 5000);
        ZkClient zkClient = new ZkClient(zkConnection);

        zkClient.subscribeChildChanges("/testZkClent", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("handleChildChange callback is " + s + list);
                System.out.println("childSize is " + list.size());
            }
        });

        zkClient.subscribeDataChanges("/testZkClent", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("handleChildChange callback is " + s + o);
                System.out.println("childSize is " + 0);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("handleDataDeleted callback is " + s );
            }
        });


        TimeUnit.SECONDS.sleep(310);
    }
}
