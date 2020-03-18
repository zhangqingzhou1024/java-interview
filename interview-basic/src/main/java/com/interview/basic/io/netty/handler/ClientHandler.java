package com.interview.basic.io.netty.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Scanner;

/**
 * @author zqz
 * @version 1.0
 * @date 2020-01-17 19:16
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HelloWorldClientHandler Active");

        //新起写数据线程
        WriteThread writeThread = new WriteThread(ctx);
        writeThread.start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HelloWorldClientHandler read Message is:" + msg);
    }


    /**
     * 写数据线程,用来进行测试
     */
    static class WriteThread extends Thread {
        ChannelHandlerContext ctx;
        //线程关闭标志位
        volatile boolean runFlag = true;

        public WriteThread(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                System.out.println("please input some message to process ...");
                Scanner scanner = new Scanner(System.in);
                while (runFlag) {
                    String msg = scanner.nextLine();
                    msg += System.lineSeparator();
                    //发送数据
                    ctx.channel().writeAndFlush(Unpooled.copiedBuffer(msg.getBytes()));
                    System.out.println("please input some message to process ...");
                }
            } catch (Exception e) {
            }
        }
    }
}
