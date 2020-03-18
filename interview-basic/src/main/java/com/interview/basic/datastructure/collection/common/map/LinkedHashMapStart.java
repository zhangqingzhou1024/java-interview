package com.interview.basic.datastructure.collection.common.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-05 21:36
 */
public class LinkedHashMapStart {
    public static void main(String[] args) {
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        linkedHashMap = new LinkedHashMap(16, 0.75f, false);


        // HashMap<Object, Object> linkedHashMap = new HashMap<>();

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
