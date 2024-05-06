package com.zhangxiaofanfan.learn.myselfdemo.server.handler;

import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.MessageRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 用户消息请求处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-20 16:40:38
 */
@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        log.info("消息请求拦截器生效, 时间: {}, 内容为: {}", new Date(), msg);
        
        MessageResponsePacket response = MessageResponsePacket
                .builder()
                .message(String.format("服务端相应数据: %s", msg.getMessage()))
                .build();
        ctx.channel().writeAndFlush(response);
    }
}
