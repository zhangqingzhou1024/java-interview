package interview.lecode.huiwen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author zqz
 * @date 2024-03-01 16:17
 */
public class NumHuiwen {

    public static void main(String[] args) {
        int x = 1234321;
        // 654321

        int reverseNum = reverseNum(x);

        System.out.println(x == reverseNum);


        LinkedList<String> strings = new LinkedList<String>();
        strings.add("a");
        strings.add("b");

        char c = "abc".charAt(1);
        String num = "abc".charAt(1)+"";
        System.out.println(c+"");

        //System.out.println(strings);


    }

    public static int reverseNum(int x) {
        int reverseNum = 0;
        while (x != 0) {
            // 升一位 要* 10
            reverseNum = reverseNum * 10 + x % 10;
            // 每次取一位，向前迭代
            x = x / 10;
        }

       String s = "aaa";
        char[] chars = s.toCharArray();

        int[] ints = new int[3];
        return reverseNum;
    }
}
