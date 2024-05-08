package com.spring;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 说明:
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6], n = 3
 * 输出: [1,2,2,3,5,6]
 *
 * @author zqz
 * @date 2024-04-01 15:42
 */
public class Test {

    public static void main(String[] args) {
        int[] nums1 = new int[6];
        nums1[0] = 1;
        nums1[1] = 2;
        nums1[2] = 3;
        nums1[3] = 0;
        nums1[4] = 0;
        nums1[5] = 0;
        int[] nums2 = new int[3];
        nums2[0] = 2;
        nums2[1] = 5;
        nums2[2] = 6;

        int[] result = mergeArr(nums1, nums2);

        for (int i : result) {
            System.out.println(i);
        }
    }

    private static int[] mergeArr(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums1;
        }
        if (nums2.length == 0) {
            return nums1;
        }

        // 排序 & 合并
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] nums3 = new int[length1];


        int n3Index = 0;
        int n1Left = 0;
        int n2Left = 0;

        while (n1Left < length1) {
            int n1 = nums1[n1Left];

            boolean flag = false;
            while (n2Left < length2) {
                flag = true;
                int n2 = nums2[n2Left];
                // 处理 0值
                if (n1 == 0) {
                    nums3[n3Index] = n2;
                    n3Index++;
                    n1Left++;
                    n2Left++;
                    break;
                }
                if (n1 < n2) {
                    nums3[n3Index] = n1;
                    n3Index++;
                    n1Left++;
                    break;
                } else {
                    nums3[n3Index] = n2;
                    n3Index++;
                    n2Left++;
                    break;
                }
            }
            // 空值迭代
            if(!flag){
                n1Left++;
            }
        }
        return nums3;
    }
}
