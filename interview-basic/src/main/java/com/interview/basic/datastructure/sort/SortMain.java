package com.interview.basic.datastructure.sort;

import com.google.common.base.Joiner;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-03-17 00:17
 */
public class SortMain {

    public static void main(String[] args) {

        Integer[] arr = {8, 5, 3, 3, 2, 4, 9, -1};
        // 冒泡排序
        //bubbleSort(arr);
        bubbleSort2(arr);
        // 选择
        //selectSort(arr);
        // 插入
        // insertSort(arr);
        // 快排
        //  quickSort(arr, 0, arr.length - 1);


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

    private static void bubbleSort2(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 大的右移动, 内层的每次找出最大的元素 放到最右边
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换
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
     * <p>
     * 冒泡排序和选择排序是两种不同的排序算法，它们在本质上并不相同。冒泡排序是一种简单的排序算法，
     * 它通过比较相邻的元素并交换它们的位置来对数组进行排序。而选择排序是一种排序算法，它通过选择数组中的最小值
     * （升序排序）或最大值（降序排序）并将其放置在正确的位置来对数组进行排序。
     * <p>
     * 冒泡排序和选择排序之间的主要区别在于它们的实现方式和性能。冒泡排序在每次比较时都会进行交换操作，
     * 而选择排序只在每次迭代结束后进行一次交换操作。因此，选择排序通常比冒泡排序执行更少的交换操作，
     * 因此在实际应用中，选择排序通常比冒泡排序更快更高效。
     * <p>
     * 总的来说，虽然冒泡排序和选择排序都属于O(n^2)的时间复杂度，但它们在实际应用中有不同的性能表现，选择排序通常更快更高效。
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
     * @param arr   原始数组
     * @param left  左
     * @param right 右指针
     */
    private static void quickSort(Integer[] arr, int left, int right) {
        // 高低位指针相遇，表示只剩一个元素
        if (left >= right) {
            return;
        }
        // 分而治之
        int pivot = partition(arr, left, right);
        // 小堆
        quickSort(arr, left, pivot - 1);
        // 大堆
        quickSort(arr, pivot + 1, right);
    }

    /**
     * 1、分区
     * 2、返回 pivot位置
     */
    private static int partition(Integer[] arr, int left, int right) {
        // 基点
        int pivot = arr[left];

        while (left < right) {
            // 处理右侧，发现小的放左边,如果right不移动，则表明右侧的都小于temp,则也进行
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];

            // 处理右侧，发现大的放右边
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        // 放入中间值，此时 left = right
        arr[left] = pivot;
        return left;
    }

    public static void mergeSort(int[] arr, int start, int end) {
        //判断拆分的不为最小单位
        if (start <= end) {
            //再一次拆分，知道拆成一个一个的数据
            mergeSort(arr, start, (start + end) / 2);
            mergeSort(arr, (start + end) / 2 + 1, end);
            //记录开始/结束位置
            int left = start;
            int right = (start + end) / 2 + 1;
            //记录每个小单位的排序结果
            int index = 0;
            int[] result = new int[end - start + 1];
            //如果查分后的两块数据，都还存在
            while (left <= (start + end) / 2 && right <= end) {
                //比较两块数据的大小，然后赋值，并且移动下标
                if (arr[left] <= arr[right]) {
                    result[index] = arr[left];
                    left++;
                } else {
                    result[index] = arr[right];
                    right++;
                }
                //移动单位记录的下标
                index++;
            }
            //当某一块数据不存在了时
            while (left <= (start + end) / 2 || right <= end) {
                //直接赋值到记录下标
                if (left <= (start + end) / 2) {
                    result[index] = arr[left];
                    left++;
                } else {
                    result[index] = arr[right];
                    right++;
                }
                index++;
            }
            //最后将新的数据赋值给原来的列表，并且是对应分块后的下标。
            for (int i = start; i <= end; i++) {
                arr[i] = result[i - start];
            }
        }
    }
}
