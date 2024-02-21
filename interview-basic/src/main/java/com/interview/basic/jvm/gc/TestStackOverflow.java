package com.interview.basic.jvm.gc;

/**
 * 线程 栈溢出
 * JVM 设置
 * <p>
 * -Xss128k， -Xss1M
 * 循环调用，栈帧被盛满
 *
 * @author zqz
 * @date 2024-02-20 16:46
 */
public class TestStackOverflow {
    private static int count = 0;

    public static void main(String[] args) {
        try {
            redo();
        } catch (Exception e) {
            e.printStackTrace();

        }
        /**
         *
         *
         * -Xss128k: 1200 次
         * -Xss1M: 11485 次
         *
         */
    }

    private static void redo() {
        count++;
        System.out.println(count);
        redo();
    }
}
