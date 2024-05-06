package com.zhangxiaofanfan.learn.handlerlogin.handler;

import com.zhangxiaofanfan.learn.handlerlogin.packet.MessageRequestPacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.MessageResponsePacket;
import com.zhangxiaofanfan.learn.handlerlogin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 处理消息请求数据包使用的 channel 处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 19:47:40
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        // 处理消息流程
        log.info("{}: 收到客户端消息: {}", new Date(), messageRequestPacket.getMessage());
        MessageResponsePacket response = new MessageResponsePacket();
        response.setMessage(String.format("服务端回复【%s】", messageRequestPacket.getMessage()));
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), response);
        ctx.channel().writeAndFlush(buffer);
    }
}
