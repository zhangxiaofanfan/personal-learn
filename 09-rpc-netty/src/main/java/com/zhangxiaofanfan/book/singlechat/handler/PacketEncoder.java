package com.zhangxiaofanfan.book.singlechat.handler;

import com.zhangxiaofanfan.book.singlechat.codec.PacketCodeC;
import com.zhangxiaofanfan.book.singlechat.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 复用 netty 提供的内置 handler 接口, 实现编码器专用 encoder
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 09:55:39
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }
}
