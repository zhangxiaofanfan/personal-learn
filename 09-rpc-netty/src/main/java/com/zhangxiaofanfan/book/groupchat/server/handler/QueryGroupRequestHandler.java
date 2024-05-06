package com.zhangxiaofanfan.book.groupchat.server.handler;

import com.zhangxiaofanfan.book.groupchat.packet.request.QueryGroupRequestPacket;
import com.zhangxiaofanfan.book.groupchat.packet.request.QuitGroupRequestPacket;
import com.zhangxiaofanfan.book.groupchat.packet.response.QueryGroupResponsePacket;
import com.zhangxiaofanfan.book.groupchat.packet.response.QuitGroupResponsePacket;
import com.zhangxiaofanfan.book.groupchat.session.Session;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 退出群聊处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:49:59
 */
public class QueryGroupRequestHandler extends SimpleChannelInboundHandler<QueryGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QueryGroupRequestPacket msg) throws Exception {
        System.out.println("查询群聊收到请求, 请求内容: " + msg);
        Session session = SessionUtil.getSession(ctx.channel());
        String groupId = msg.getGroupId();
        QueryGroupResponsePacket response = new QueryGroupResponsePacket();
        ChannelGroup group = SessionUtil.getChannelGroup(groupId);
        if (group == null || !group.contains(ctx.channel())) {
            // 退出群聊失败, 将失败消息推送给加群用户
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
            // 查询群聊成功, 将成功消息推送给群聊中所有用户
            List<String> userList = new ArrayList<>();
            group.forEach(channel -> {
                Session groupUserSession = SessionUtil.getSession(channel);
                if (groupUserSession != null) {
                    userList.add(groupUserSession.getUserName());
                }
            });
            response.setGroupId(groupId);
            response.setUserId(session.getUserId());
            response.setSuccess(true);
            response.setUserNameList(userList);
            group.writeAndFlush(response);
        }
    }
}
