package com.interview.basic.io.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * 配置处理器
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-17 23:23
 */
public class ServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                .addLast("decoder", new StringDecoder())
                .addLast("encoder", new StringEncoder())
                .addLast(new ServerBeforeHandler())
                .addLast(new ServerAfterHandler());
    }
}
