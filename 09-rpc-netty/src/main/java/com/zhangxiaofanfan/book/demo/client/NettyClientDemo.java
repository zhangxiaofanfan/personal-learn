package com.zhangxiaofanfan.book.demo.client;

import com.zhangxiaofanfan.book.demo.client.handler.ClientHandler;
import com.zhangxiaofanfan.book.demo.client.handler.FirstClientHandler;
import com.zhangxiaofanfan.book.demo.codec.PacketCodeC;
import com.zhangxiaofanfan.book.demo.handler.ConnectionHandler;
import com.zhangxiaofanfan.book.demo.handler.LoginRequestHandler;
import com.zhangxiaofanfan.book.demo.handler.LoginResponseHandler;
import com.zhangxiaofanfan.book.demo.handler.MessageResponseHandler;
import com.zhangxiaofanfan.book.demo.handler.PacketDecoder;
import com.zhangxiaofanfan.book.demo.handler.PacketEncoder;
import com.zhangxiaofanfan.book.demo.packet.pojo.MessageRequestPacket;
import com.zhangxiaofanfan.book.demo.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-12 09:23:28
 */
public class NettyClientDemo {
    private static final Integer MAX_RETRY = 10;
    public static void main(String[] args) {
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // ch.pipeline().addLast(new FirstClientHandler());
                        // ch.pipeline().addLast(new ClientHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new ConnectionHandler());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
    }

    public static void connect(final Bootstrap bootstrap, final String address, final int port, int retry) {
        bootstrap.connect(address, port).addListener(future -> {
            if (future.isSuccess()) {
                // 表示端口绑定成功
                System.out.printf("地址[%s]端口[%s]绑定成功\n", address, port);
                // 后台启动控制台, 用于用户输入聊天数据
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry <= 0) {
                // 表示绑定端口失败
                System.out.printf("地址[%s]端口[%s]绑定失败, 放弃端口绑定\n", address, port);
            } else {
                int errorCount = MAX_RETRY - retry + 1;
                int time = 1 << errorCount;
                System.out.printf(
                        "时间: %s:地址[%s]端口[%s]绑定失败, 尝试绑定端口[%s], 失败次数: %s, 等待时间: %ss\n",
                        new Date(),
                        address,
                        port,
                        port,
                        errorCount,
                        time
                );
                bootstrap.config().group().schedule(() -> connect(bootstrap, address, port, retry - 1), time, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入聊天内容, 按回车键结束");
        Thread consoleThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                // 完成登录之后才可以进行聊天沟通
                // if (LoginUtil.hasLogin(channel)) {
                System.out.print(">: ");
                String input = scanner.nextLine();
                MessageRequestPacket message = new MessageRequestPacket();
                message.setMessage(input);
                ByteBuf buffer = PacketCodeC.INSTANCE.encode(channel.alloc(), message);
                channel.writeAndFlush(buffer);
                // }
            }
        });
        consoleThread.start();
        new Thread(() -> {
            while (!LoginUtil.hasLogin(channel)) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            consoleThread.interrupt();
        }).start();
    }
}
