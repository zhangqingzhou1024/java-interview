package com.interview.basic.jvm;

import com.interview.basic.jvm.gc.bean.Person;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author zqz
 * @date 2024-02-20 17:58
 */
public class JOLTest {

    public static void main(String[] args) {

        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());
        System.out.println();
        System.out.println();
        /**
         * 对象的内存分配
         * 包括对象头（markword|klassPoint）、实例数据、对齐数据
         */
           /*java.lang.Object object internals:
        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        -------  MarkWord --------------
        8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
        ------- klassPoint --------------
        12     4        (loss due to the next object alignment)
         ------- 对齐数据， 必须为8的的倍数 --------------
        Instance size: 16 bytes
        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total*/

        classLayout = ClassLayout.parseInstance(new Person(3, 6));
        System.out.println(classLayout.toPrintable());
        System.out.println();
        System.out.println();

      /*  com.interview.basic.jvm.gc.bean.Person object internals:
        OFFSET  SIZE                                  TYPE DESCRIPTION                               VALUE
        0     4                                       (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        4     4                                       (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        8     4                                       (object header)                           d0 df 00 f8 (11010000 11011111 00000000 11111000) (-134160432)
        12     4                                   int Person.c                                  3
        16     8                                  long Person.d                                  6
        24     4   com.interview.basic.jvm.gc.bean.Man Person.man                                (object)
        ------- man对象指针的引用，理论上应该是8，但是现在是4，是因为开启了指针压缩 --------------
        28     4                                       (loss due to the next object alignment)
        Instance size: 32 bytes
        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
*/

      /*
      对象指针压缩：
      1.6之后开始支持，现在默认开启
        -XX:+UseCompressedOops 开启，默认开启
        -XX:-UseCompressedOops 关闭
        -XX:-UseCompressedOops 关闭
         ---
         如果不进行压缩，内存使用大概为1.5倍，使用大指针在主内存和缓存之间移动数据， 占用更多的IO资源，同事GC压力也更大
       com.interview.basic.jvm.gc.bean.Person object internals:
        OFFSET  SIZE                                  TYPE DESCRIPTION                               VALUE
        0     4                                       (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
        4     4                                       (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        8     4                                       (object header)                           d0 91 14 14 (11010000 10010001 00010100 00010100) (336892368)
        12     4                                       (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
        16     8                                  long Person.d                                  6
        24     4                                   int Person.c                                  3
        28     4                                       (alignment/padding gap)
        32     8   com.interview.basic.jvm.gc.bean.Man Person.man1                               (object)
        40     8   com.interview.basic.jvm.gc.bean.Man Person.man2                               null
        Instance size: 48 bytes
        Space losses: 4 bytes internal + 0 bytes external = 4 bytes total*/

    }
}
