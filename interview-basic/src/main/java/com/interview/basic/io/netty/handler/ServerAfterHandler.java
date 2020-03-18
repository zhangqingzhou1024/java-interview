package com.interview.basic.io.netty.handler;

import com.interview.basic.io.netty.BusinessProcessThread;
import com.interview.basic.io.netty.TaskMsgObj;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * netty server request handler
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-17 19:12
 */
public class ServerAfterHandler extends ChannelInboundHandlerAdapter {

    /**
     * 业务处理线程
     */
    static ThreadPoolExecutor businessProcessThreadPoolExecutor = new ThreadPoolExecutor(10, 10, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory());

    /**
     * 表明已经成功构建连接
     *
     * @param ctx 上下文信息
     * @throws Exception 异常处理
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerAfterHandler active");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerAfterHandler channelRead..");
        System.out.println(ctx.channel().remoteAddress() + "->Server :" + msg.toString());

        // 封装消息体
        TaskMsgObj build = TaskMsgObj.builder().ctx(ctx).msg(msg).taskTime(System.currentTimeMillis()).build();

        // 提交业务处理任务
        BusinessProcessThread processThread = new BusinessProcessThread(build);
        businessProcessThreadPoolExecutor.execute(processThread);
    }
}
