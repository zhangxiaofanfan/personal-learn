package com.zhangxiaofanfan.book.groupchat.server.handler;

import com.zhangxiaofanfan.book.groupchat.packet.request.JoinGroupRequestPacket;
import com.zhangxiaofanfan.book.groupchat.packet.response.JoinGroupResponsePacket;
import com.zhangxiaofanfan.book.groupchat.session.Session;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * 加入群聊处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:49:59
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        System.out.println("加入群聊收到请求, 请求内容: " + msg);
        Session session = SessionUtil.getSession(ctx.channel());
        String groupId = msg.getGroupId();
        JoinGroupResponsePacket response = new JoinGroupResponsePacket();
        ChannelGroup group = SessionUtil.getChannelGroup(groupId);
        if (group == null) {
            // 加入群聊失败, 将失败消息推送给加群用户
            response.setGroupId(groupId);
            response.setUserId(session.getUserId());
            response.setSuccess(false);
            response.setReason(String.format("群聊账号[%s]不存在", groupId));
            ctx.channel().writeAndFlush(response);
        } else {
            // 加入群聊成功, 将成功消息推送给群聊中所有用户
            group.add(ctx.channel());
            response.setGroupId(groupId);
            response.setUserId(session.getUserId());
            response.setSuccess(true);
            group.writeAndFlush(response);
        }
    }
}
