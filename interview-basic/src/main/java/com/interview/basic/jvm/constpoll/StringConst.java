package com.interview.basic.jvm.constpoll;

/**
 * 字符串常量池
 *
 * @author zqz
 * @date 2024-02-23 0:08
 */
public class StringConst {
    public static void main(String[] args) {

    /*  String s0="zhuge";
        String s1="zhuge";
        String s2="zhu" + "ge";
        System.out.println( s0==s1 ); //true
        System.out.println( s0==s2 ); //true*/


      /*  String s0="zhuge";
        String s1=new String("zhuge");
        String s2="zhu" + new String("ge");
        System.out.println( s0==s1 );// false
        System.out.println( s0==s2 );// false
        System.out.println( s1==s2 );// false*/


     /*   String a = "a1";
        String b = "a" + 1;
        System.out.println(a == b); // true*/

        String a = "atrue";
        String b = "a" + "true";
        System.out.println(a == b); // true

     /*   String a = "a3.4";
        String b = "a" + 3.4;
        System.out.println(a == b); // true*/
    }

}
