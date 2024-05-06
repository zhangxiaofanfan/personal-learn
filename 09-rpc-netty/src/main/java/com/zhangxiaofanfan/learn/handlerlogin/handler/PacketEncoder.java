package com.zhangxiaofanfan.learn.handlerlogin.handler;

import com.zhangxiaofanfan.learn.handlerlogin.packet.Packet;
import com.zhangxiaofanfan.learn.handlerlogin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 译码器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 17:33:31
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
