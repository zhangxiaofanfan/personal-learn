package com.zhangxiaofanfan.book.groupchat.server.handler;

import com.zhangxiaofanfan.book.groupchat.packet.response.MessageResponsePacket;
import com.zhangxiaofanfan.book.groupchat.session.Session;
import com.zhangxiaofanfan.book.groupchat.packet.request.MessageRequestPacket;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 处理用户登录请求使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 10:01:36
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(msg.getMessage());
        messageResponsePacket.setVersion(msg.getVersion());

        Channel toChannel = SessionUtil.getChannel(msg.getToUserId());

        if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
            toChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("【" + msg.getToUserId() + "】不在线, 发送消息失败!");
        }
    }
}
