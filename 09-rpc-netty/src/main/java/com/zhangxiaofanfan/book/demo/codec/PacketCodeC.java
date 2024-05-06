package com.zhangxiaofanfan.book.demo.codec;

import com.zhangxiaofanfan.book.demo.command.Command;
import com.zhangxiaofanfan.book.demo.packet.Packet;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.LoginResponsePacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageRequestPacket;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageResponsePacket;
import com.zhangxiaofanfan.book.demo.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

import static com.zhangxiaofanfan.book.demo.serializer.Serializer.DEFAULT;
import static com.zhangxiaofanfan.book.demo.serializer.Serializer.JDK_INSTANCE;
import static com.zhangxiaofanfan.book.demo.serializer.SerializerAlgorithm.JDK;
import static com.zhangxiaofanfan.book.demo.serializer.SerializerAlgorithm.JSON;

/**
 * 数据包加解密
 *  内容:  |  魔数  |  版本号  |  算法号  |  对象类型  |  数据内容长度  |  数据内容  |
 *  长度:    4字节     1字节      1字节      1字节        4字节           N字节
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 19:53:37
 */
public class PacketCodeC {
    private final Map<Byte, Class<? extends Packet>> map = new HashMap<>();
    {
        map.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        map.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        map.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        map.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public static final int MAGIC_NUMBER = 0x12345678;

    /**
     * 编码方法
     *
     * @param packet 编码数据包对象
     * @return 填充有数据对象二进制的 buffer 对象
     */
    public ByteBuf encode(ByteBufAllocator allocator, Packet packet) {
        // 1. 创建 ByteBuf 对象, 用来接收数据
        ByteBuf buffer = allocator.ioBuffer();
        // 2. 序列化 java 对象
        byte[] content = DEFAULT.serialize(packet);
        // 3. 编码过程
        buffer.writeInt(MAGIC_NUMBER);
        buffer.writeByte(packet.getVersion());
        buffer.writeByte(DEFAULT.getSerializeAlgorithm());
        buffer.writeByte(packet.getCommand());
        buffer.writeInt(content.length);
        buffer.writeBytes(content);

        return buffer;
    }

    /**
     * 编码方法
     *
     * @param packet 编码数据包对象
     * @return 填充有数据对象二进制的 buffer 对象
     */
    public ByteBuf encode(ByteBuf buffer, Packet packet) {
        // 1. 序列化 java 对象
        byte[] content = DEFAULT.serialize(packet);
        // 2. 编码过程
        buffer.writeInt(MAGIC_NUMBER);
        buffer.writeByte(packet.getVersion());
        buffer.writeByte(DEFAULT.getSerializeAlgorithm());
        buffer.writeByte(packet.getCommand());
        buffer.writeInt(content.length);
        buffer.writeBytes(content);

        return buffer;
    }

    /**
     * 解码方法
     *
     * @param buffer 带有解码数据的 buffer 对象
     * @return buffer 中的 java 对象
     */
    public Packet decode(ByteBuf buffer) {
        // 1. 魔数
        buffer.skipBytes(4);
        // 2. 版本号
        buffer.skipBytes(1);
        // 3. 算法号
        byte algorithmNum = buffer.readByte();
        // 4. 对象类型
        byte versionNumber = buffer.readByte();
        // 5. 内容长度
        int contentLength = buffer.readInt();
        // 6. 真实内容
        byte[] content = new byte[contentLength];
        buffer.readBytes(content);
        // 7. 将数据转换成真实的 java 对象
        // 7.1 根据算法编号查询出序列化对象
        Serializer serializer = getSerializer(algorithmNum);
        // 7.2 根据对象指令查询出需要反序列化的类对象
        Class<? extends Packet> packetClass = getPacketClass(versionNumber);
        if (serializer != null && packetClass != null) {
            return serializer.deserialize(packetClass, content);
        }
        return null;
    }

    /**
     * 根据数据包指令查询类对象
     *
     * @param command 对象指令
     * @return 数据包对应的类对象
     */
    public Class<? extends Packet> getPacketClass(byte command) {
        return map.getOrDefault(command, null);
    }

    /**
     * 根据算法编号获取对应的序列化对象
     *
     * @param algorithm 算法编号
     * @return 编号对应的序列化对象
     */
    private Serializer getSerializer(byte algorithm) {
        switch (algorithm) {
            case JSON -> {
                return DEFAULT;
            }
            case JDK -> {
                return JDK_INSTANCE;
            }
            default -> {
                return null;
            }
        }
    }
}
