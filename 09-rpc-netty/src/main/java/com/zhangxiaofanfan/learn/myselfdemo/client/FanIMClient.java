package com.zhangxiaofanfan.learn.myselfdemo.client;

import com.zhangxiaofanfan.learn.myselfdemo.client.handler.LoginResponseHandler;
import com.zhangxiaofanfan.learn.myselfdemo.client.handler.LogoutResponseHandler;
import com.zhangxiaofanfan.learn.myselfdemo.codec.DivisionDecode;
import com.zhangxiaofanfan.learn.myselfdemo.codec.PacketCodec;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.MessageRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.util.LoginUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 使用 netty 自定义客户端实现
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 14:28:52
 */
@Slf4j
public class FanIMClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9359;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new DivisionDecode());
                        ch.pipeline().addLast(new PacketCodec());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new LogoutResponseHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                log.info("{}: 连接成功!, channel对象是: {}, 后台启动控制台线程...", new Date(), channel);
                LoginUtils.consoleLogin(channel);
                startConsoleThread(channel);
            } else if (retry == 0) {
                log.error("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                log.warn("{}: 连接失败，第 {} 次重连......", new Date(), order);
                bootstrap
                        .config()
                        .group()
                        .schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (!Thread.interrupted()) {
                if (LoginUtils.hasLogin(channel)) {
                    log.info("输入消息发送至服务端: ");
                    String line = scanner.nextLine();
                    MessageRequestPacket message = new MessageRequestPacket();
                    message.setMessage(line);
                    channel.writeAndFlush(message);
                }
            }
        }).start();
    }
}
