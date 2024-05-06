package com.zhangxiaofanfan.learn.myselfdemo.client.handler;

import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 连接完成之后进行的回调登录
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 17:31:33
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket login = new LoginRequestPacket();

        login.setUsername("zhangxiaofanfan");
        login.setPassword("yangxiaomianmian");

        ctx.channel().writeAndFlush(login);
    }
}
