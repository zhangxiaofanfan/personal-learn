package com.zhangxiaofanfan.book.demo.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 当链接完成之后 server 的第一个逻辑处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-13 12:27:10
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        String clientData = buffer.toString(StandardCharsets.UTF_8);
        System.out.printf("%s: 服务端读取到数据 --> %s\n", new Date(), clientData);
        System.out.printf("%s: 服务端读写出数据\n", new Date());
        // 1. 准备数据
        ByteBuf out = getByteBuf(ctx, clientData);
        // 2. 写回数据
        ctx.channel().writeAndFlush(out);
    }


    private ByteBuf getByteBuf(ChannelHandlerContext ctx, String clientData) {
        // 1. 获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();
        // 2. 准备数据, 发送数据通过 UTF-8 进行编码
        byte[] bytes = String.format("来自服务器【张小帆帆】的响应数据, 数据: %s", clientData)
                .getBytes(StandardCharsets.UTF_8);
        // 3. 数据填充
        buffer.writeBytes(bytes);
        return buffer;
    }
}
