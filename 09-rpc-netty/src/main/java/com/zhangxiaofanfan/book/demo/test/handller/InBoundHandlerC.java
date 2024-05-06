package com.zhangxiaofanfan.book.demo.test.handller;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 09:35:28
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerC: " + msg);
        super.channelRead(ctx, msg);
    }
}
