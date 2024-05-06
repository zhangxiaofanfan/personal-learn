package com.zhangxiaofanfan.learn.handlerlogin.handler;

import com.zhangxiaofanfan.learn.handlerlogin.packet.LoginRequestPacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.LoginResponsePacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.MessageRequestPacket;
import com.zhangxiaofanfan.learn.handlerlogin.protocol.PacketCodeC;
import com.zhangxiaofanfan.learn.handlerlogin.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;

/**
 * 处理登录响应数据包使用的 channel 处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 17:32:23
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername("zhangxiaofanfan");
        loginRequestPacket.setPassword("yangxiaomianmian");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
        if (loginResponsePacket.isSuccess()) {
            log.info("{}: 客户端登录成功", new Date());
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            log.error("{}: 客户端登录失败，原因: {}", new Date(), loginResponsePacket.getReason());
        }
    }
}
