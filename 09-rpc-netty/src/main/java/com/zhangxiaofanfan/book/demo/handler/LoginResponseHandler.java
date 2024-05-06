package com.zhangxiaofanfan.book.demo.handler;

import com.zhangxiaofanfan.book.demo.packet.pojo.LoginRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginResponsePacket;
import com.zhangxiaofanfan.book.demo.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * 处理用户登录请求使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 10:01:36
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        // 处理登录请求
        if (msg.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            System.out.printf("%s: 客户端登录成功\n", new Date());
        } else {
            System.out.printf("%s: 客户端登录失败, 失败原因: %s\n", new Date(), msg.getReason());
        }
    }
}
