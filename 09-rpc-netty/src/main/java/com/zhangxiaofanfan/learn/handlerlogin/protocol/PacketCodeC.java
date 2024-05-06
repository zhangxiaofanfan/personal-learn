package com.zhangxiaofanfan.learn.handlerlogin.protocol;

import com.zhangxiaofanfan.learn.handlerlogin.packet.LoginRequestPacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.LoginResponsePacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.MessageRequestPacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.MessageResponsePacket;
import com.zhangxiaofanfan.learn.handlerlogin.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.zhangxiaofanfan.learn.login.protocol.Command.LOGIN_REQUEST;
import static com.zhangxiaofanfan.learn.login.protocol.Command.LOGIN_RESPONSE;
import static com.zhangxiaofanfan.learn.login.protocol.Command.MESSAGE_REQUEST;
import static com.zhangxiaofanfan.learn.login.protocol.Command.MESSAGE_RESPONSE;

/**
 * 数据包加解密类
 *
 * @date 2024-02-19 10:08:07
 * @author zhangxiaofanfan
 */
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;


    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    public ByteBuf encode(ByteBuf buffer, Packet packet) {
        // 2. 序列化 java 对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3. 实际编码过程
        buffer.writeInt(MAGIC_NUMBER);
        buffer.writeByte(packet.getVersion());
        buffer.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        buffer.writeByte(packet.getCommand());
        buffer.writeInt(bytes.length);
        buffer.writeBytes(bytes);

        return buffer;
    }


    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);
        // 跳过版本号
        byteBuf.skipBytes(1);
        // 序列化算法
        byte serializeAlgorithm = byteBuf.readByte();
        // 指令
        byte command = byteBuf.readByte();
        // 数据包长度
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);
        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}