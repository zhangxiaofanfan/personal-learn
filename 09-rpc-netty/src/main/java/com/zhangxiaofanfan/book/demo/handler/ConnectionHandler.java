package com.zhangxiaofanfan.book.demo.handler;

import com.zhangxiaofanfan.book.demo.codec.PacketCodeC;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 11:28:01
 */
public class ConnectionHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端与服务端连接完成时, 立即发送登录请求
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.printf("%s: 客户端开始登录\n", new Date());

        // 创建登录对象
        LoginRequestPacket login = new LoginRequestPacket();
        login.setUserId(UUID.randomUUID().toString());
        login.setUsername("zhangxiaofanfan");
        login.setPassword("yangxiaomianmian");

        // // 编码
        // ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), login);

        // 写入数据
        // ctx.channel().writeAndFlush(login);
    }
}
