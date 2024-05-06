package com.zhangxiaofanfan.book.groupchat.client.handler;

import com.zhangxiaofanfan.book.groupchat.packet.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 客户端端处理创建群聊响应使用的 handler
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 09:22:32
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.printf("创建群聊成功, 群聊id【%s】, 群聊成员为: 【%s】\n", msg.getGroupId(), String.join(",", msg.getUserNameList()));
    }
}
