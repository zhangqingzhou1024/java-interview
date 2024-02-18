package com.interview.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 对以原声客户端做了一部分封装的zkClient
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-08 23:39
 */
public class TestZkClient {
    public static void main(String[] args) throws Exception {

        // 获取连接
        ZkConnection zkConnection = new ZkConnection("127.0.0.1:2181", 5000);
        ZkClient zkClient = new ZkClient(zkConnection);

        // zkClient.createEphemeral("/testZkClent/01");
        zkClient.createPersistent("/testZkClent/01", true);

        List<String> children = zkClient.getChildren("/testZkClent");
        children.forEach(node -> {
            System.out.println(node);
        });


        zkClient.delete("/testZkClent/01",-1);

        zkClient.writeData("/testZkClent","test");
        //zkClient.deleteRecursive("/testZkClent");
        TimeUnit.SECONDS.sleep(10);

    }
}
