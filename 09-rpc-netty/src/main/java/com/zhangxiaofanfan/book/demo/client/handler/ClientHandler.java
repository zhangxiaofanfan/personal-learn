package com.zhangxiaofanfan.book.demo.client.handler;

import com.zhangxiaofanfan.book.demo.codec.PacketCodeC;
import com.zhangxiaofanfan.book.demo.packet.Packet;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginResponsePacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageResponsePacket;
import com.zhangxiaofanfan.book.demo.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * 客户端逻辑处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 22:11:32
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

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

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), login);

        // 写入数据
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buffer);
        if (packet instanceof LoginResponsePacket loginResponse) {
            // 处理登录请求
            if (loginResponse.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                System.out.printf("%s: 客户端登录成功\n", new Date());
            } else {
                System.out.printf("%s: 客户端登录失败, 失败原因: %s\n", new Date(), loginResponse.getReason());
            }
        } else if (packet instanceof MessageResponsePacket message) {
            System.out.printf("%s: 收到服务端消息: %s\n", new Date(), message.getMessage());
        }
    }
}
