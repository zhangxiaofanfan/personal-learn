package com.zhangxiaofanfan.book.demo.handler;

import com.zhangxiaofanfan.book.demo.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * 验证登录相关的处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 18:27:06
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            // 已经完成登录验证, 后续连接可以不用再执行此操作
            ctx.pipeline().remove(this);
            System.out.println("服务端登录成功验证!");
            super.channelRead(ctx, msg);
        } else {
            System.out.println("服务端没有成功登录!");
            ctx.channel().close();
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.printf("%s: 当前连接登录已完成验证, 无需再次验证, %s被移除!\n", new Date(), this.getClass().getName());
        } else {
            System.out.printf("%s: 无需登录验证, %s被移除!\n", new Date(), this.getClass().getName());
        }
    }
}
