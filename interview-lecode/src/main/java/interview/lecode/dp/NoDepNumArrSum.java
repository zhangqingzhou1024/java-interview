package interview.lecode.dp;

/**
 * 给定一个正整数数组求选择不相邻的数字相加之和最大为多少？
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-01 22:26
 */
public class NoDepNumArrSum {

    public static void main(String[] args) {

        int[] arr = {4, 1, 1, 9, 19, 20};
        int n = arr.length;

        int rec_sum = rec_sum(arr, n);
        System.out.println(rec_sum);

        int dp_sum = dp_sum(arr);
        System.out.println(dp_sum);
    }

    /**
     * 递归求解
     *
     * @param arr 正整数数组
     * @param n   数组前元素 n
     * @return 不相邻数字相加之和最大值
     */
    public static int rec_sum(int[] arr, int n) {
        if (n == 1) {
            return arr[0];
        } else if (n == 2) {
            return Math.max(arr[0], arr[1]);

        } else {
            int A = arr[n - 1] + rec_sum(arr, n - 2);
            int B = rec_sum(arr, n - 1);


            int max = Math.max(A, B);
            return max;
        }
    }


    /**
     * 动态规划解法
     *
     * @param arr 正整数数组
     * @return 不相邻数字相加之和最大值
     */
    public static int dp_sum(int[] arr) {
        int[] resultArr = new int[arr.length];
        resultArr[0] = arr[0];
        resultArr[1] = Math.max(arr[0], arr[1]);

        for (int n = 2; n < arr.length; n++) {
            int A = arr[n] + resultArr[n - 2];
            int B = resultArr[n - 1];
            resultArr[n] = Math.max(A, B);
        }
        return resultArr[arr.length - 1];
    }
}
