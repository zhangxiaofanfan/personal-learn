package com.zhangxiaofanfan.learn.handlerlogin.handler;

import com.zhangxiaofanfan.learn.handlerlogin.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * 编码器
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 17:31:44
 */
public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
