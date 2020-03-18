package com.interview.basic.datastructure.collection.common.list;

/**
 * ArrayList 学习
 * https://note.youdao.com/ynoteshare1/index.html?id=d841efe40d7f1018b4d168c8444adf2c&type=note
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-04 21:23
 */
public class ArrayListStart {

    public static void main(String[] args) {

        // 1. arrayList 基于数组实现，初始化默认值操作，如 初实话容量=10
        java.util.ArrayList<Object> arrayList = new java.util.ArrayList<>();

        // 2. list中数组扩容操作
        arrayList.add("test");
        arrayList.add("test02");

        arrayList.add(1, 2);

        // 3。数组中后续元素重copy
        arrayList.remove(0);
        arrayList.remove("test");

        for (Object o : arrayList) {
            System.out.println(o);
        }

        // 4. 直接定位数据索引位置，效率较高
        Object o = arrayList.get(0);
        System.out.println(o);


        java.util.ArrayList<Object> arrayListTest = new java.util.ArrayList<>(1000);
        arrayListTest.add(1, "222");
        arrayListTest.add(900, "222");
        arrayListTest.add(500, "222");

    }
}
