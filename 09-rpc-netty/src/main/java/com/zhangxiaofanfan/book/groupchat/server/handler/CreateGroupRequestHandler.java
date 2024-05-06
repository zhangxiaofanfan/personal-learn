package com.zhangxiaofanfan.book.groupchat.server.handler;

import com.zhangxiaofanfan.book.groupchat.packet.request.CreateGroupRequestPacket;
import com.zhangxiaofanfan.book.groupchat.packet.response.CreateGroupResponsePacket;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 服务端处理创建群聊请求使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 09:22:32
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> userIdList = msg.getUserIdList();
        List<String> userNameList = new ArrayList<>();

        // 创建 channel 组, 用来绑定群聊在线的所有用户
        ChannelGroup group = new DefaultChannelGroup(ctx.executor());

        userIdList.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                group.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        });

        CreateGroupResponsePacket response = new CreateGroupResponsePacket();
        response.setSuccess(true);
        response.setGroupId(UUID.randomUUID().toString());
        response.setUserNameList(userNameList);

        SessionUtil.bindChannelGroup(response.getGroupId(), group);

        group.writeAndFlush(response);
        System.out.printf("创建群聊成功, 群聊id【%s】, 群聊成员为: 【%s】\n", response.getGroupId(), String.join(",", userNameList));
    }
}
