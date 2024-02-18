package interview.lecode.dp;

/**
 * 斐波那楔数列
 * 每个数的值都是一个状态，可以用F[i]表示表示第i个数的值是多少。每个数都是由F[i-1]+F[i-2]转移而来。
 * 1 1 2 3 5 8 13 ...
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-01 21:15
 */
public class FibonacciSequence {

    public static void main(String[] args) {
        int n = 50;
        long start_recu = System.currentTimeMillis();
        int recu_fs = recu_fs(n);
        long end_recu = System.currentTimeMillis();
        System.out.println("耗时 " + (end_recu - start_recu) + " ms,recu_fs ==>" + recu_fs);

        int dp_fs = dp_fs(n);
        long end_dp = System.currentTimeMillis();
        System.out.println("耗时 " + (end_dp - end_recu) + " ms,dp_fs ==>" + dp_fs);

    }
    /**
     * 递归方法实现
     *
     * @param n 斐波那契数列
     * @return 数列中第n个数的值
     */
    public static int recu_fs(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return recu_fs(n - 1) + recu_fs(n - 2);
        }
    }

    /**
     * 动态规划方法
     *
     * @param n 斐波那契数列
     * @return 数列中第n个数的值
     */
    public static int dp_fs(int n) {
        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n - 1];
    }
}
