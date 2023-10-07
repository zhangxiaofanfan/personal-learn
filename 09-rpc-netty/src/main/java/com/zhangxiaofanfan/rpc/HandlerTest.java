package com.zhangxiaofanfan.rpc;

import com.zhangxiaofanfan.rpc.generator.SessionIdGenerator;
import com.zhangxiaofanfan.rpc.message.Message;
import com.zhangxiaofanfan.rpc.message.RpcRequestMessage;
import com.zhangxiaofanfan.rpc.protocol.MessageCodecSharable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 下午12:36
 * @description TODO
 */
public class HandlerTest {
    public static void main(String[] args) {

        // EmbeddedChannel channel = new EmbeddedChannel(new MessageCodecSharable());
        //
        // RpcRequestMessage message = new RpcRequestMessage();
        // message.setSessionId(SessionIdGenerator.nextId());
        // message.setMessageType(message.getMessageType());
        // channel.writeOutbound(message);
        //
        // ByteBuf outResult = channel.readOutbound();
        // System.out.println(outResult);
        //
        // channel.writeInbound(outResult);
        // Message inResult = channel.readInbound();
        // System.out.println(inResult);
    }
}
