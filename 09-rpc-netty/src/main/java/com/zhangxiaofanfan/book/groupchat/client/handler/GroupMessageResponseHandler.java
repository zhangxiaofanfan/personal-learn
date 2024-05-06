package com.zhangxiaofanfan.book.groupchat.client.handler;

import com.zhangxiaofanfan.book.groupchat.packet.response.SendGroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 加入群聊处理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:49:59
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<SendGroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendGroupMessageResponsePacket msg) throws Exception {
        if (msg.isSuccess()) {
            System.out.printf("用户[%s]向群聊[%s]发消息: %s!\n", msg.getUserId(), msg.getGroupId(), msg.getMessage());
        } else {
            System.err.printf("用户[%s]退出群聊[%s]失败, 失败原因是: %s!\n", msg.getUserId(), msg.getGroupId(), msg.getReason());
        }
    }
}
