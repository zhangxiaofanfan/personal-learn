package com.zhangxiaofanfan.book.groupchat.server.handler;

import com.zhangxiaofanfan.book.groupchat.packet.request.LoginRequestPacket;
import com.zhangxiaofanfan.book.groupchat.session.Session;
import com.zhangxiaofanfan.book.groupchat.packet.response.LoginResponsePacket;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
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
        boolean state = SessionUtil.login(msg.getUsername(), msg.getPassword());
        response.setSuccess(state);
        response.setVersion(msg.getVersion());
        if (!state) {
            response.setReason("账号密码错误, 登录失败");
        } else {
            // 表示登录成功, 绑定会话信息
            // String userId = UUID.randomUUID().toString();
            response.setUserId(msg.getUsername());
            SessionUtil.bindSession(
                    Session
                            .builder()
                            .userId(msg.getUsername())
                            .userName(msg.getUsername())
                            .build(),
                    ctx.channel());
        }
        System.out.println("客户端收到数据: " + msg + "返回响应数据 + " + response);
        ctx.channel().writeAndFlush(response);
    }


    // 用户断线之后取消绑定
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开连接......");
        SessionUtil.unBindSession(ctx.channel());
    }
}
