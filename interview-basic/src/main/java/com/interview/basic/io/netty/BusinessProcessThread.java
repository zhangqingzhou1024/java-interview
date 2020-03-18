package com.interview.basic.io.netty;

import io.netty.channel.ChannelHandlerContext;

/**
 * 业务处理线程
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-17 18:00
 */
public class BusinessProcessThread implements Runnable {

    /**
     * 任务信息
     */
    TaskMsgObj taskMsgObj;

    public BusinessProcessThread(TaskMsgObj taskMsgObj) {
        this.taskMsgObj = taskMsgObj;
    }

    @Override
    public void run() {
        if (null == taskMsgObj) {
            return;
        }
        System.out.println(Thread.currentThread().getName() + " 开始处理任务...");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("taskMsg ==>" + taskMsgObj);

        ChannelHandlerContext handlerContext = taskMsgObj.getCtx();
        handlerContext.write("server write is " + taskMsgObj.getMsg());
        handlerContext.flush();

        System.out.println(Thread.currentThread().getName() + " 处理完成...");
    }
}
