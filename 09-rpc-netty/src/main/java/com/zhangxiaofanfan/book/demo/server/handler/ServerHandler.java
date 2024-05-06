package com.zhangxiaofanfan.book.demo.server.handler;

import com.zhangxiaofanfan.book.demo.codec.PacketCodeC;
import com.zhangxiaofanfan.book.demo.packet.Packet;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginResponsePacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * 服务端逻辑处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 22:12:14
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buffer = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buffer);
        LoginResponsePacket response = new LoginResponsePacket();
        response.setVersion(packet.getVersion());
        if (packet instanceof LoginRequestPacket login) {
            if (valid(login)) {
                // 校验成功
                response.setSuccess(true);
            } else {
                // 校验失败
                response.setSuccess(false);
                response.setReason("账户密码验证失效");
            }
            ByteBuf responseBuffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), response);
            ctx.channel().writeAndFlush(responseBuffer);
        } else if (packet instanceof MessageRequestPacket message) {
            // 处理消息请求
            System.out.printf("%s: 收到客户端消息: %s\n", new Date(), message.getMessage());
            ctx.channel().writeAndFlush(
                    PacketCodeC.INSTANCE.encode(
                            ctx.alloc(),
                            MessageResponsePacket
                                    .builder()
                                    .message(String.format("客户端回复: 【%s】", message.getMessage()))
                                    .build()
                    )
            );
        }
    }

    private boolean valid(LoginRequestPacket login) {
        System.out.printf("username is %s\n", login.getUsername());
        System.out.printf("password is %s\n", login.getPassword());
        return true;
    }
}
