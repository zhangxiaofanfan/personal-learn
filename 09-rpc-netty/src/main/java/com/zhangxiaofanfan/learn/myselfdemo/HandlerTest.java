package com.zhangxiaofanfan.learn.myselfdemo;

import com.zhangxiaofanfan.learn.myselfdemo.codec.PacketCodec;
import com.zhangxiaofanfan.learn.myselfdemo.packet.Packet;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * 用来测试自定义 handler 使用的测试类
 *
 * @date 2024-02-19 05:14:37
 * @author zhangxiaofanfan
 */
@Slf4j
public class HandlerTest {
    public static void main(String[] args) {

        EmbeddedChannel channel = new EmbeddedChannel(new PacketCodec());
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername("zhangxiaofanfan");
        packet.setPassword("yangxiaomianmian");
        channel.writeOutbound(packet);

        ByteBuf outResult = channel.readOutbound();
        log.info("输入结果为: {}", outResult);

        channel.writeInbound(outResult);
        Packet inResult = channel.readInbound();
        log.info("输出结果为: {}", inResult);
    }
}
