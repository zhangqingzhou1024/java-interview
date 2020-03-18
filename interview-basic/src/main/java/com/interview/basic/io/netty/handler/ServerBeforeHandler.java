package com.interview.basic.io.netty.handler;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * server handler 处理
 * @author zqz
 * @version 1.0
 * @date 2020-01-17 19:47
 */
public class ServerBeforeHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerBeforeHandler active");
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("ServerBeforeHandler channelRead");

        if(null == msg ){
            return;
        }

        String msgStr = msg.toString();
        int length = msgStr.length();
        if(length <= 0 ){
            return;
        }
        System.out.println("ServerBeforeHandler befor is "+msg);
        msg =  "-1&&" + msgStr;
        System.out.println("ServerBeforeHandler after is "+msg);

        ctx.fireChannelRead(msg);
    }
}
