package interview.lecode;

/**
 * @author zqz
 * @date 2024-03-04 19:47
 */
public class Test {

    public static void main(String[] args) {
        /**
         * 位运算
         */

        int a = 2;
        int b = 2;
        int c = 3;
        int d = 4;

        System.out.println("a&b ==>" + (a & b));
        System.out.println("a&c ==>" + (a & c));
        System.out.println("c&a ==>" + (c & a));

        /**
         * 异或 ^
         *  a^
         */

        System.out.println("2^2 ==>" + (2^2));
        System.out.println("2^3 ==>" + (2^3));
        System.out.println("7^8 ==>" + (7^8));
        System.out.println("7^8^9 ==>" + (7^8^9));
        System.out.println("7^8^8 ==>" + (7^8^8));

    }
}
