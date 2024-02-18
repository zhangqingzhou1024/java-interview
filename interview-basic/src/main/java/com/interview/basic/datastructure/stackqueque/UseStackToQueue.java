package com.interview.basic.datastructure.stackqueque;

import java.util.Stack;

/**
 * 利用栈的特性实现队列
 *
 * @author zqz
 * @version 1.0
 * @date 2020-03-24 10:25
 */
public class UseStackToQueue<T> {


    /**
     * 负责接受数据的栈
     */
    private Stack<T> pushStack = new Stack<>();

    /**
     * 负责弹出的栈
     */
    private Stack<T> popStack = new Stack<>();


    /**
     * 入队操作
     *
     * @param data
     */
    public void push(T data) {
        if (null == data) {
            throw new NullPointerException("data not null!");
        }

        pushStack.push(data);
    }

    /**
     * 出队操作
     *
     * @return
     */
    public T pop() {

        // 没有数据
        if (popStack.empty() && pushStack.empty()) {
            return null;
        }

        // 如果弹出的容器有数据，则可以直接弹出
        if (!popStack.empty()) {
            return popStack.pop();
        }

        // 要进行数据转移
        if (popStack.empty() && !pushStack.empty()) {
            while (!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
        // 进行弹出
        return popStack.pop();
    }

    public static void main(String[] args) {
        UseStackToQueue<String> stackToQueue = new UseStackToQueue<>();
        stackToQueue.push("A");
        stackToQueue.push("B");
        stackToQueue.push("C");
        stackToQueue.push("D");


        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
        System.out.println(stackToQueue.pop());
    }

}
