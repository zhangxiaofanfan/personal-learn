package com.zhangxiaofanfan.learn.handlerlogin.handler;

import com.zhangxiaofanfan.learn.handlerlogin.packet.LoginRequestPacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.LoginResponsePacket;
import com.zhangxiaofanfan.learn.handlerlogin.utils.LoginUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 处理登录请求数据包使用的 channel 处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 19:47:01
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        // 处理登录登录流程
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        Channel channel = ctx.channel();
        if (valid(loginRequestPacket)) {
            LoginUtil.markAsLogin(channel);
            loginResponsePacket.setSuccess(true);
            log.info("{}: 登录成功!", new Date());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            log.warn("{}: 登录失败!", new Date());
        }
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return "zhangxiaofanfan".equals(loginRequestPacket.getUsername()) &&
                "yangxiaomianmian".equals(loginRequestPacket.getPassword());
    }
}
