package com.zhangxiaofanfan.learn.myselfdemo.codec;

import com.zhangxiaofanfan.learn.myselfdemo.command.Commands;
import com.zhangxiaofanfan.learn.myselfdemo.packet.Packet;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginResponsePacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LogoutRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.MessageRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.MessageResponsePacket;
import com.zhangxiaofanfan.learn.myselfdemo.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据包编解码器, 基于自定义数据格式
 *  |  魔数  |  版本号  |  序列号算法  |  指令  |  数据长度  |  数据  |
 *     4B       1B          1B         1B        4B      NB(字节)
 *
 * @date 2024-02-19 03:36:26
 * @author zhangxiaofanfan
 */
public class PacketCodec extends MessageToMessageCodec<ByteBuf, Packet> {
    public static final PacketCodec INSTANCE = new PacketCodec();
    private static final Integer MAGIC_NUM = 0x12345678;
    private static final Map<Commands, Class<? extends Packet>> packetTypeMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Commands.LOGIN_REQUEST_PACKET, LoginRequestPacket.class);
        packetTypeMap.put(Commands.LOGIN_RESPONSE_PACKET, LoginResponsePacket.class);
        packetTypeMap.put(Commands.MESSAGE_REQUEST_PACKET, MessageRequestPacket.class);
        packetTypeMap.put(Commands.MESSAGE_RESPONSE_PACKET, MessageResponsePacket.class);
        packetTypeMap.put(Commands.LOGOUT_REQUEST_PACKET, LogoutRequestPacket.class);
        packetTypeMap.put(Commands.LOGOUT_RESPONSE_PACKET, LoginResponsePacket.class);
    }

    /**
     * 编码器
     *
     * @param ctx channel 对象
     * @param msg 待编码的数据包对象
     * @param out 保存编码之后的数据
     * @throws Exception 编码过程出现非预期异常
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        // 1. 4字节模数
        buffer.writeInt(MAGIC_NUM);
        // 2. 1字节版本号
        buffer.writeByte(msg.getVersion());
        // 3. 1字节序列算法
        buffer.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        // 4. 1字节指令类型
        buffer.writeByte(msg.getCommand());
        // 5. 4字节内容长度
        byte[] data = Serializer.DEFAULT.serialize(msg);
        buffer.writeInt(data.length);
        buffer.writeBytes(data);
        // 6. 将数据防止在 out 对象上，等待数据传输
        out.add(buffer);
    }

    // 译码器
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
        // 1. 1字节模数
        int magicNum = buffer.readInt();
        // 2. 1字节版本号
        byte version = buffer.readByte();
        // 3. 1字节序列化方式
        byte algorithmNum = buffer.readByte();
        byte command = buffer.readByte();
        // 4. 4字节内容长度
        int contentLength = buffer.readInt();
        // 5. 实际内容
        byte[] contentBytes = new byte[contentLength];
        buffer.readBytes(contentBytes, 0, contentLength);
        // 6. 将数据防止在 out 对象上，等待数据传输
        out.add(getPacketByCommand(contentBytes, command, algorithmNum));
    }

    private Serializer getSerializerAlgorithmByCondition(byte algorithmNum) {
        return Serializer.DEFAULT;
    }

    private Packet getPacketByCommand(byte[] content, byte command, byte algorithmNum) {
        Serializer serializer = getSerializerAlgorithmByCondition(algorithmNum);
        Commands commandObj = Commands.getCommandByByte(command);
        if (commandObj != null && packetTypeMap.containsKey(commandObj)) {
            return serializer.deserialize(packetTypeMap.get(commandObj), content);
        }
        return null;
    }
}
