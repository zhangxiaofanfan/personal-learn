package com.zhangxiaofanfan.book.groupchat.client.handler;

import com.zhangxiaofanfan.book.groupchat.packet.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理用户登录请求使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 10:01:36
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.printf("%s:%s -> %s\n", msg.getFromUserId(), msg.getFromUserName(), msg.getMessage());
    }
}
