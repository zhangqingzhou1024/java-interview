package com.interview.basic.jvm.constpoll;

/**
 * @author zqz
 * @date 2024-02-23 0:10
 */
public class BaseType8Const  {

    public static void main(String[] args) {

        //5种整形的包装类Byte,Short,Integer,Long,Character的对象，
        //在值小于127时可以使用对象池
        Integer i1 = 127;  //这种调用底层实际是执行的Integer.valueOf(127)，里面用到了IntegerCache对象池
        Integer i2 = 127;
        int i1_ = 127;
        System.out.println(i1 == i2);//输出true
        System.out.println(i1 == i1_);//输出true, NVOKEVIRTUAL java/lang/Integer.intValue ()I
        //值大于127时，不会从对象池中取对象
        Integer i3 = 128; // Integer.valueOf
        Integer i4 = 128; // Integer.valueOf
        Integer i3_ = 128;// Integer.valueOf
        System.out.println(i3 == i4);//输出false
        System.out.println(i3 == i3_);//输出false

        //用new关键词新生成对象不会使用对象池
        Integer i5 = new Integer(127);
        Integer i6 = new Integer(127);
        System.out.println(i5 == i6);//输出false

        //Boolean类也实现了对象池技术
        Boolean bool1 = true;
        Boolean bool2 = true;
        System.out.println(bool1 == bool2);//输出true

        //浮点类型的包装类没有实现对象池技术
        Double d1 = 1.0;
        Double d2 = 1.0;
        System.out.println(d1 == d2);//输出false
    }
}
