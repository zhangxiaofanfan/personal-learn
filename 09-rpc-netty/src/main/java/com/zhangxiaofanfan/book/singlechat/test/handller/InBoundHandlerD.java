package com.zhangxiaofanfan.book.singlechat.test.handller;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 09:35:28
 */
public class InBoundHandlerD extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("InBoundHandlerD: " + msg);
        super.write(ctx, msg, promise);
    }
}
