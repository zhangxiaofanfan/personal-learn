package com.zhangxiaofanfan.rpc.protocol;

import com.zhangxiaofanfan.rpc.message.Message;
import com.zhangxiaofanfan.rpc.annotation.MySharable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 上午9:37
 * @description 消息编码/译码器, 现阶段只使用了 JDK 自带的序列化方式, 后续还会加入 JSON 序列化方式
 */
@MySharable     // 可被共享
public class MessageCodecSharable extends MessageToMessageCodec<ByteBuf, Message> {
    // 编码器
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        // 1. 1字节模数
        buffer.writeBytes(new byte[]{9,3,5,9});
        // 2. 1字节版本号
        buffer.writeByte(1);
        // 3. 1字节序列化方式
        buffer.writeByte(0);
        // 4. 1字节指令类型
        buffer.writeByte(msg.getMessageType());
        // 5. 1字节数据填充
        buffer.writeByte(0xff);
        // 6. 4字节会话序号
        buffer.writeInt(msg.getSessionId());
        // 7. 4字节内容长度
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        // byte[] content = JSONUtil.parseObj(msg).toString().getBytes();
        byte[] content = bos.toByteArray();
        buffer.writeInt(content.length);
        // 8. 实际内容
        buffer.writeBytes(content);
        // 9. 将数据防止在 out 对象上，等待数据传输
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
        byte serializerType = buffer.readByte();
        // 4. 1字节指令类型
        byte messageType = buffer.readByte();
        // 5. 1字节数据填充
        buffer.readByte();
        // 6. 4字节会话序号
        int sessionId = buffer.readInt();
        // 7. 4字节内容长度
        int contentLength = buffer.readInt();
        // 8. 实际内容
        byte[] contentBytes = new byte[contentLength];
        buffer.readBytes(contentBytes, 0, contentLength);
        // String jsonStr = new String(contentBytes);
        // Message message = JSONUtil.toBean(jsonStr, Message.class);
        ObjectInputStream ios = new ObjectInputStream(new ByteArrayInputStream(contentBytes));
        Message message = (Message)ios.readObject();
        // 9. 将数据防止在 out 对象上，等待数据传输
        out.add(message);
    }
}
