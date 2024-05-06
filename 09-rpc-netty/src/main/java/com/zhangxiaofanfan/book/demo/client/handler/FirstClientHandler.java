package com.zhangxiaofanfan.book.demo.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 当链接完成之后 client 的第一个逻辑处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-13 08:57:59
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("%s: 客户端写出数据\n", new Date());
        // 1. 准备数据
        ByteBuf buffer = getByteBuf(ctx);
        // 2. 写回数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 2. 准备数据, 发送数据通过 UTF-8 进行编码
        byte[] bytes = "你好, 张小帆帆!".getBytes(StandardCharsets.UTF_8);
        // 3. 数据填充
        buffer.writeBytes(bytes);
        return buffer;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        System.out.printf("%s: 服务端读取到数据 --> %s\n", new Date(), buffer.toString(StandardCharsets.UTF_8));
    }
}
