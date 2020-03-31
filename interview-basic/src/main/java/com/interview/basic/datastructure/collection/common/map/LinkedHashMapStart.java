package com.interview.basic.datastructure.collection.common.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * LinkedHashMap 是HashMap 的一个子类，拥有父亲==类所有的作用，
 * 除此之外 内部会维护一个链表，用来表示插入顺序的，afterNodeInsertion(evict);
 * 在迭代的时候，是按这这个链表去读取的
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-05 21:36
 */
public class LinkedHashMapStart {
    public static void main(String[] args) {
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap = new LinkedHashMap(16, 0.75f, false);


        // HashMap<Object, Object> linkedHashMap = new HashMap<>();
        // 维护一个链表
        //  afterNodeInsertion(evict);
        linkedHashMap.put("5", 22);
        linkedHashMap.put("1", 22);
        linkedHashMap.put("2", 22);
        linkedHashMap.put("3", 22);
        linkedHashMap.put("4", 22);

        linkedHashMap.get("2");
        linkedHashMap.get("4");

        Set<Object> objects = linkedHashMap.keySet();

        for (Object object : objects) {
            System.out.println(object);
        }
    }
}
