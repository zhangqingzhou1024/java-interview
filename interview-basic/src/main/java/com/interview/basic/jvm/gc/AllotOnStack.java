package com.interview.basic.jvm.gc;

/**
 * @author zqz
 * @date 2024-02-20 23:34
 */

import com.interview.basic.datastructure.jdk8stream.User;

/**
 * 栈上分配，标量替换
 * 代码调用了1亿次alloc()，如果是分配到堆上，大概需要1GB以上堆空间，如果堆空间小于该值，必然会触发GC。
 * <p>
 * 使用如下参数不会发生GC或很少量
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * 使用如下参数都会发生大量GC
 * -Xmx15m -Xms15m -XX:-DoEscapeAnalysis -XX:+PrintGC -XX:+EliminateAllocations
 * -Xmx15m -Xms15m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-EliminateAllocations
 */
public class AllotOnStack {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    /**
     * 方法结束方法对应的栈帧会销毁的
     * 所以可以这样，gc 这么少
     */
    private static void alloc() {
        User user = new User();
        user.setId(1);
        user.setName("aaa");
    }
}
