package com.zhangxiaofanfan.book.groupchat.client.handler;

import com.zhangxiaofanfan.book.groupchat.packet.response.JoinGroupResponsePacket;
import com.zhangxiaofanfan.book.groupchat.packet.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 加入群聊处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:49:59
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.printf("用户[%s]退出群聊[%s]成功!\n", msg.getUserId(), msg.getGroupId());
        } else {
            System.err.printf("用户[%s]退出群聊[%s]失败, 失败原因是: %s!\n", msg.getUserId(), msg.getGroupId(), msg.getReason());
        }
    }
}
