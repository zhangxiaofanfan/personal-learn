package com.zhangxiaofanfan.book.demo.handler;

import com.zhangxiaofanfan.book.demo.packet.pojo.MessageRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 处理用户登录请求使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 10:01:36
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        // 处理消息请求
        System.out.printf("%s: 收到客户端消息: %s\n", new Date(), msg.getMessage());
        ctx.channel().writeAndFlush(
                MessageResponsePacket
                        .builder()
                        .message(String.format("客户端回复: 【%s】", msg.getMessage()))
                        .build()
        );
    }
}
