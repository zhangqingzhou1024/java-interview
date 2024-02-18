package com.interview.basic;

import com.google.common.base.Joiner;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-25 15:33
 */
public class GetTopMin {

    public static void main(String[] args) {
        Integer[] dataArr = {8, 5, 3, 3, 2, 4, 8, 5, 3, 3, 2, 4, 10, 11, 19, 29, 10, 1, 0, 59, 9};


        // 不重复的数据
        HashSet<Integer> noRepitNumSet = new HashSet<>();
        noRepitNumSet.addAll(Arrays.asList(dataArr));

        // 进行排序
        Integer[] allNoRepitDataArr = noRepitNumSet.toArray(new Integer[0]);

        // 低位
        int low = 0;
        // 高位
        int high = allNoRepitDataArr.length - 1;

        // 进行排序
        quickSort(allNoRepitDataArr, low, high);

        Integer[] targetArr = new Integer[10];
        if (allNoRepitDataArr.length > 10) {
            for (int i = 0; i < 10; i++) {
                targetArr[i] = allNoRepitDataArr[i];
            }
        } else {
            targetArr = allNoRepitDataArr;
        }
        // 打印

        TreeSet<Integer> treeSet = treeSet(dataArr);
        PriorityQueue<Integer> priorityQueue = buildPriorityQueue(dataArr);

        System.out.println("targetArr：" + Joiner.on(",").join(targetArr));
        System.out.println("treeSet：" + Joiner.on(",").join(treeSet));

        System.out.println("-----------priorityQueue-----------");
        while (!priorityQueue.isEmpty()) {
            System.out.print(priorityQueue.poll() + " ");
        }

        System.out.println("-----------priorityQueu");
        usedStream(dataArr);
    }

    /**
     * 快排
     *
     * @param dataArr
     * @param low
     * @param high
     */
    private static void quickSort(Integer[] dataArr, int low, int high) {

        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;

        int flagVal = dataArr[left];

        while (left < right) {
            // 右移动
            while (left < right && dataArr[right] >= flagVal) {
                right--;
            }
            if (left < right) {
                dataArr[left] = dataArr[right];
            }
            // 左移动
            while (left < right && dataArr[left] < flagVal) {
                left++;
            }
            if (left < right) {
                dataArr[right] = dataArr[left];
            }

            // 终止条件
            if (left >= right) {
                dataArr[left] = flagVal;
            }

            // 二分迭代
            quickSort(dataArr, 0, right);
            quickSort(dataArr, right + 1, high);
        }
    }


    /**
     * 利用 treeSet 的结构特性
     *
     * @param dataArr
     * @return
     */
    public static TreeSet<Integer> treeSet(Integer[] dataArr) {
        TreeSet<Integer> treeSet = new TreeSet<>(Arrays.asList(dataArr));

        System.out.println(treeSet);
        return treeSet;

    }

    /**
     * 优先队列
     *
     * @param dataArr
     */
    public static PriorityQueue<Integer> buildPriorityQueue(Integer[] dataArr) {

        //自定义比较器，降序排列
      /*   Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        };*/

        Comparable comparable =    new Comparable<Integer>(){

          @Override
          public int compareTo(Integer o) {
              return 0;
          }
      };

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(dataArr));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10);


        priorityQueue.addAll(arrayList);
        return priorityQueue;
    }

    public static void usedStream(Integer[] dataArr){

        Stream<Integer> integerStream = Stream.of(dataArr).distinct().sorted().limit(10);

        integerStream.forEach(num ->{
            System.out.println(num);
        });

    }

}
