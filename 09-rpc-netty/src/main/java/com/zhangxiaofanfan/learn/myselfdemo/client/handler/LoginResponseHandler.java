package com.zhangxiaofanfan.learn.myselfdemo.client.handler;

import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginResponsePacket;
import com.zhangxiaofanfan.learn.myselfdemo.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 登录响应拦截器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 17:29:35
 */
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        log.info("登录响应拦截器生效, 时间: {}, 内容为: {}", new Date(), msg);
        if (msg.isSuccess()) {
            ctx.channel().attr(LoginUtils.SESSION_KEY).set(msg.getSession());
            log.info("登录成功");
        } else {
            log.warn("登录失败, 请重新登录");
            LoginUtils.consoleLogin(ctx.channel());
        }
    }
}
