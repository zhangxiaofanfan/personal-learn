package com.zhangxiaofanfan.learn.myselfdemo.server.handler;

import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginResponsePacket;
import com.zhangxiaofanfan.learn.myselfdemo.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;

/**
 * 登录请求拦截器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 17:20:13
 */
@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        // 校验用户
        log.info("登录请求拦截器生效, 时间: {}, 内容为: {}", new Date(), msg);
        String session = LoginUtils.login(msg.getUsername(), msg.getPassword(), ctx.channel());
        LoginResponsePacket loginResponse = LoginResponsePacket
                .builder()
                .success(Strings.isNotEmpty(session))
                .session(session)
                .build();
        log.debug("登录响应为: {}", loginResponse);
        ctx.channel().writeAndFlush(loginResponse);
    }
}
