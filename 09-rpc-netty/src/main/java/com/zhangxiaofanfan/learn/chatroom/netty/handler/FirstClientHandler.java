package com.zhangxiaofanfan.learn.chatroom.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 15:33:05
 */
@Slf4j
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{}: 客户端写出数据", new Date());
        // 1. 获取数据
        ByteBuf buffer = getByteBuf(ctx);
        // 2. 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，张小帆帆!".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        return buffer;
    }
}
