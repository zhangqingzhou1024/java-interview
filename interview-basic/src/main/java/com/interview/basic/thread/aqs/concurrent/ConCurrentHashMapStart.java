package com.interview.basic.thread.aqs.concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-09 16:54
 */
public class ConCurrentHashMapStart {
    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        concurrentHashMap.put(1,1);
        concurrentHashMap.get(1);

    }
}


