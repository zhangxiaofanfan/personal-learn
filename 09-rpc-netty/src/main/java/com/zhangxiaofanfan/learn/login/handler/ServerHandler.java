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

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-02-05 14:30:30
 */
@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date() + ": 客户端开始登录......");
        ByteBuf requestByteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        if (packet instanceof LoginRequestPacket loginRequestPacket) {
            // 处理登录登录流程
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            Channel channel = ctx.channel();
            if (valid(loginRequestPacket)) {
                LoginUtil.markAsLogin(channel);
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }
            // 登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            channel.writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket messageRequestPacket) {
            // 处理消息流程
            log.info("{}: 收到客户端消息: {}", new Date(), messageRequestPacket.getMessage());
            MessageResponsePacket response = new MessageResponsePacket();
            response.setMessage(String.format("服务端回复【%s】", messageRequestPacket.getMessage()));
            ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), response);
            ctx.writeAndFlush(buffer);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return "zhangxiaofanfan".equals(loginRequestPacket.getUsername()) &&
                "yangxiaomianmian".equals(loginRequestPacket.getPassword());
    }
}
