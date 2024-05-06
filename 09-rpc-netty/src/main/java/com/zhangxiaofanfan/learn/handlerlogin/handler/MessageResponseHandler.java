package com.zhangxiaofanfan.learn.handlerlogin.handler;

import com.zhangxiaofanfan.learn.handlerlogin.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 处理消息响应数据包使用的 channel 处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 17:33:01
 */
@Slf4j
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        log.info("{}: 收到服务端的消息: {}", new Date(), messageResponsePacket.getMessage());
    }
}
