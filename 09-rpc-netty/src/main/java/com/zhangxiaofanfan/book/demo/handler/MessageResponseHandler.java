package com.zhangxiaofanfan.book.demo.handler;

import com.zhangxiaofanfan.book.demo.packet.pojo.LoginResponsePacket;
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
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        System.out.printf("%s: 收到服务端消息: %s\n", new Date(), msg.getMessage());
    }
}
