package com.zhangxiaofanfan.learn.chatroom.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 15:41:23
 */
@Slf4j
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 接收到
        ByteBuf byteBuf = (ByteBuf)msg;
	    log.info("{}: 服务端读到数据 -> {}", new Date(), byteBuf.toString(StandardCharsets.UTF_8));
    }
}
