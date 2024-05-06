package com.zhangxiaofanfan.learn.login.handler;

import com.zhangxiaofanfan.learn.login.packet.LoginRequestPacket;
import com.zhangxiaofanfan.learn.login.packet.LoginResponsePacket;
import com.zhangxiaofanfan.learn.login.packet.MessageRequestPacket;
import com.zhangxiaofanfan.learn.login.packet.MessageResponsePacket;
import com.zhangxiaofanfan.learn.login.packet.Packet;
import com.zhangxiaofanfan.learn.login.protocol.PacketCodeC;
import com.zhangxiaofanfan.learn.login.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-02-05 14:14:01
 */
@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端开始登录");

        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("zhangxiaofanfan");
        loginRequestPacket.setPassword("yangxiaomianmian");

        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if (packet instanceof LoginResponsePacket loginResponsePacket) {
            if (loginResponsePacket.isSuccess()) {
                System.out.println(new Date() + ": 客户端登录成功");
                // 启动控制台线程
                startConsoleThread(ctx.channel());
            } else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
            }
        } else if (packet instanceof MessageResponsePacket messageResponsePacket) {
            log.info("{}: 收到服务端的消息: {}", new Date(), messageResponsePacket.getMessage());
        }
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    log.info("输入消息发送至服务端: ");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.nextLine();
                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    ByteBuf buffer = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                    channel.writeAndFlush(buffer);
                }
            }
        }).start();
    }
}
