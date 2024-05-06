package com.zhangxiaofanfan.book.singlechat.handler;

import com.zhangxiaofanfan.book.singlechat.packet.pojo.LoginResponsePacket;
import com.zhangxiaofanfan.book.singlechat.util.SessionUtil;
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
            SessionUtil.markAsLogin(ctx.channel());
            System.out.printf("%s: 客户端登录成功, 响应内容为: %s\n", new Date(), msg);
        } else {
            System.out.printf("%s: 客户端登录失败, 失败原因: %s\n", new Date(), msg.getReason());
        }
    }
}
