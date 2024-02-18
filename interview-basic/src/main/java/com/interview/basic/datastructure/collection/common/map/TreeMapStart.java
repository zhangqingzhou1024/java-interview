package com.interview.basic.datastructure.collection.common.map;

import java.util.TreeMap;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-25 22:05
 */
public class TreeMapStart {

    public static void main(String[] args) {
        TreeMap<Object, Object> objectObjectTreeMap = new TreeMap<>();

        // 内部维护一个红黑树  fixAfterInsertion(e);
        objectObjectTreeMap.put(1,2);
    }
}
