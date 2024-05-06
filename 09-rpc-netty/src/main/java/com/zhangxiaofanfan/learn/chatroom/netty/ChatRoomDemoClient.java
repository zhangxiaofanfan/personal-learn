package com.zhangxiaofanfan.learn.chatroom.netty;

import com.zhangxiaofanfan.learn.chatroom.netty.handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 使用 netty 实现的聊天室客户端
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 10:15:37
 */
public class ChatRoomDemoClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8080).channel();
        while (true) {
            channel.writeAndFlush(new Date() + ": hello world！");
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
