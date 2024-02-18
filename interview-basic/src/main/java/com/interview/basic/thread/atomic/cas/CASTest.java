package com.interview.basic.thread.atomic.cas;

import sun.misc.Unsafe;

/**
 * @author zqz
 * @date 2024-02-16 22:10
 */
public class CASTest {
    public static void main(String[] args) throws Exception {
        Point o = new Point();

        Unsafe unsafe  = sun.misc.Unsafe.getUnsafe();;
        long objectFieldOffset = unsafe.objectFieldOffset(o.getClass().getDeclaredField("x"));
        boolean compareAndSwapInt = unsafe.compareAndSwapInt(o, objectFieldOffset, 0, 3);
        System.out.println(compareAndSwapInt);
        System.out.println(o);
    }


    static class Point{
        int x;

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    '}';
        }
    }
}
