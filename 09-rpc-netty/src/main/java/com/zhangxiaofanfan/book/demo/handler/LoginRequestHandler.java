package com.zhangxiaofanfan.book.demo.handler;

import com.zhangxiaofanfan.book.demo.packet.pojo.LoginRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginResponsePacket;
import com.zhangxiaofanfan.book.demo.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理用户登录请求使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 10:01:36
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket response = new LoginResponsePacket();
        boolean state = LoginUtil.login(msg.getUsername(), msg.getPassword());
        response.setSuccess(state);
        response.setVersion(msg.getVersion());
        if (!state) {
            response.setReason("账号密码错误, 登录失败");
        }
        ctx.channel().writeAndFlush(response);
    }
}
