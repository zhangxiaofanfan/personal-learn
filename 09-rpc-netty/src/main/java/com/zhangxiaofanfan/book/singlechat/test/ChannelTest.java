package com.zhangxiaofanfan.book.singlechat.test;

import com.zhangxiaofanfan.book.singlechat.test.handller.InBoundHandlerA;
import com.zhangxiaofanfan.book.singlechat.test.handller.InBoundHandlerB;
import com.zhangxiaofanfan.book.singlechat.test.handller.InBoundHandlerC;
import com.zhangxiaofanfan.book.singlechat.test.handller.InBoundHandlerD;
import com.zhangxiaofanfan.book.singlechat.test.handller.InBoundHandlerE;
import com.zhangxiaofanfan.book.singlechat.test.handller.InBoundHandlerF;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import io.netty.channel.embedded.EmbeddedChannel;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 09:34:49
 */
public class ChannelTest {
    public static void main(String[] args) {
        EmbeddedChannel channel = new EmbeddedChannel(
                new InBoundHandlerA(),
                new InBoundHandlerB(),
                new InBoundHandlerC(),
                new InBoundHandlerD(),
                new InBoundHandlerE(),
                new InBoundHandlerF()
        );

        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUsername("zhangxiaofanfan");
        packet.setPassword("yangxiaomianmian");

        channel.writeInbound(packet);

        channel.writeOutbound(packet);
    }
}
