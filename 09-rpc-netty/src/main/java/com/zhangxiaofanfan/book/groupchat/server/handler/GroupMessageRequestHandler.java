package com.zhangxiaofanfan.book.groupchat.server.handler;

import com.zhangxiaofanfan.book.groupchat.packet.request.SendGroupMessageRequestPacket;
import com.zhangxiaofanfan.book.groupchat.packet.response.SendGroupMessageResponsePacket;
import com.zhangxiaofanfan.book.groupchat.session.Session;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 退出群聊处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:49:59
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<SendGroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendGroupMessageRequestPacket msg) throws Exception {
        System.out.println("发送群聊消息收到请求, 请求内容: " + msg);
        Session session = SessionUtil.getSession(ctx.channel());
        String groupId = msg.getGroupId();
        SendGroupMessageResponsePacket response = new SendGroupMessageResponsePacket();
        ChannelGroup group = SessionUtil.getChannelGroup(groupId);
        if (group == null || !group.contains(ctx.channel())) {
            // 发送群聊消息失败, 将失败消息推送给加群用户
            response.setGroupId(groupId);
            response.setUserId(session.getUserId());
            response.setSuccess(false);
            response.setReason(
                    group == null ?
                            String.format("群聊账号[%s]不存在", groupId) :
                            String.format("群聊账户[%s]不存在用户[%s]", groupId, session.getUserName())
            );
            ctx.channel().writeAndFlush(response);
        } else {
            // 发送群聊消息成功, 将成功消息推送给群聊中所有用户
            response.setGroupId(groupId);
            response.setUserId(session.getUserId());
            response.setSuccess(true);
            response.setMessage(msg.getMessage());
            group.forEach(channel -> {
                if (channel != ctx.channel()) {
                    channel.writeAndFlush(response);
                }
            });
        }
    }
}
