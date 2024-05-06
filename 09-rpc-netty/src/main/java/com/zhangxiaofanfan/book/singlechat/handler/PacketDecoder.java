package com.zhangxiaofanfan.book.singlechat.handler;

import com.zhangxiaofanfan.book.singlechat.codec.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 复用 netty 提供的内置 handler 接口, 实现解码器专用 decoder
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 09:55:39
 */
public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
