package com.interview.basic.cache;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存Map 测试类
 *
 * @author zqz
 * @version 1.0
 * @date 2020-04-01 22:05
 */
public class GuavaMapTest {

    /**
     * 初始化 本地Map
     */
    ConcurrentLruMap<String, Object> concurrentLruMap = new ConcurrentLruMap<>(2, 3, 3, 5);

    @Before
    public void initData() {
        concurrentLruMap.put("1", 222);
        concurrentLruMap.put("2", 222);
        concurrentLruMap.put("3", 222);
        concurrentLruMap.put("4", 222);
        concurrentLruMap.put("5", 222);
        concurrentLruMap.put("6", 222);
    }


    @Test
    public void testExpireTime() throws InterruptedException {
        // 测试容量大小
        System.out.println(concurrentLruMap.size());


        // 测试有效期
        System.out.println(concurrentLruMap.getValue("1"));
        System.out.println(concurrentLruMap.getValue("5"));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(concurrentLruMap.getValue("5"));
    }


    @Test
    public void testRemoveKey() {
        // 测试容量大小
        System.out.println(concurrentLruMap.size());
        concurrentLruMap.remove("2");
        // 测试容量大小
        System.out.println(concurrentLruMap.size());

    }
}
