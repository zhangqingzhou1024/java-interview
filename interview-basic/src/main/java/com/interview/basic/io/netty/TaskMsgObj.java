package com.interview.basic.io.netty;

import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息体
 *
 * @author zqz
 * @version 1.0
 * @date 2020-01-17 17:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskMsgObj {
    /**
     * 通道内线程
     */
    private ChannelHandlerContext ctx;

    /**
     * 消息体
     */
    private Object msg;

    /**
     * 接受任务时间
     */
    private long taskTime;
}
