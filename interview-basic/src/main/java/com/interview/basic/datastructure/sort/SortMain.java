package com.interview.basic.datastructure.sort;

import com.google.common.base.Joiner;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-17 00:17
 */
public class SortMain {

    public static void main(String[] args) {

        Integer[] arr = {8, 5, 3, 3, 2, 4, 9};
        // 冒泡排序
         bubbleSort(arr);
        // 选择
        //selectSort(arr);
        // 插入
        // insertSort(arr);
        // 快排
        // quickSort(arr, 0, arr.length - 1);


        System.out.println(Joiner.on(",").join(arr));

    }

    /**
     * 插入排序
     *
     * @param arr
     */
    private static void insertSort(Integer[] arr) {
        //插入排序
        for (int i = 1; i < arr.length; i++) {
            //外层循环，从第二个开始比较
            for (int j = i; j > 0; j--) {
                //内存循环，与前面排好序的数据比较，如果后面的数据小于前面的则交换
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                } else {
                    //如果不小于，说明插入完毕，退出内层循环
                    break;
                }
            }
        }
    }

    /**
     * 冒泡排序
     * -外层遍历：
     * 第2次遍历完成会找到最大值，放在最右侧
     * 第2次遍历完成会找到最2大值，放在最从右边大第二位
     * ...
     * 依次类推
     *
     * @param arr 排序数组
     */
    private static void bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //内层循环，升序大的向右，小大向左
            for (int j = 0; j < arr.length - i - 1; j++) {
                //内层循环一次，获取一个最大值
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 说明：将i前面的数据看成一个排好的队列，
     * i后面的看成一个无序队列。每次只需要找无需的最小值，做替换
     *
     * @param arr 排序数组
     */
    private static void selectSort(Integer[] arr) {
        //选择
        for (int i = 0; i < arr.length; i++) {
            //默认第一个是最小的。
            int min = arr[i];
            //记录最小的下标
            int index = i;
            //通过与后面的数据进行比较得出，最小值和下标
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            //然后将最小值与本次循环的，开始值交换
            int temp = arr[i];
            arr[i] = min;
            arr[index] = temp;

        }
    }

    /**
     * 快速排序
     *
     * @param arr
     * @param low
     * @param high
     */
    private static void quickSort(Integer[] arr, int low, int high) {
        // 高低位指针相遇
        if (low >= high) {
            return;
        }

        // 左指针
        int left = low;
        // 右指针
        int right = high;

        // 标志位
        int pivot = arr[left];

        while (left < right) {
            // ---右指针开始移动---
            // 如果右指针对应的值大于标志值，那么继续向左移动指针
            while (left < right & arr[right] >= pivot) {
                right--;
            }
            // 碰见右侧值小于标志值的，要停止指针移动，并把当前值复制到标志位上
            if (left < right) {
                arr[left] = arr[right];
            }

            // ---左指针开始移动---
            while (left < right && arr[left] < pivot) {
                left++;
            }
            // 停止本次左指针移动
            if (left < right) {
                arr[right] = arr[left];
            }

            // 停止移动标志
            if (left >= right) {
                arr[left] = pivot;
            }
        }

        // 分而治之
        quickSort(arr, low, right - 1);
        quickSort(arr, right + 1, right);
    }
}
