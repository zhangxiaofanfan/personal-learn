package com.zhangxiaofanfan.learn.myselfdemo.client.handler;

import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LogoutResponsePacket;
import com.zhangxiaofanfan.learn.myselfdemo.util.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 退出响应拦截器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 17:29:35
 */
@Slf4j
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {
        log.info("退出响应拦截器生效, 时间: {}, 内容为: {}", new Date(), msg);
        if (msg.isSuccess()) {
            ctx.channel().attr(LoginUtils.SESSION_KEY).set("");
        } else {
            log.warn("退出失败");
        }
    }
}
