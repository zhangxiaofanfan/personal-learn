package com.zhangxiaofanfan.book.groupchat.client;

import com.zhangxiaofanfan.book.groupchat.client.handler.GroupMessageResponseHandler;
import com.zhangxiaofanfan.book.groupchat.client.handler.JoinGroupResponseHandler;
import com.zhangxiaofanfan.book.groupchat.client.handler.QueryGroupResponseHandler;
import com.zhangxiaofanfan.book.groupchat.client.handler.QuitGroupResponseHandler;
import com.zhangxiaofanfan.book.groupchat.command.impl.ConsoleCommandManager;
import com.zhangxiaofanfan.book.groupchat.command.impl.LoginConsoleCommand;
import com.zhangxiaofanfan.book.groupchat.client.handler.CreateGroupResponseHandler;
import com.zhangxiaofanfan.book.groupchat.client.handler.LoginResponseHandler;
import com.zhangxiaofanfan.book.groupchat.client.handler.MessageResponseHandler;
import com.zhangxiaofanfan.book.groupchat.handler.PacketDecoder;
import com.zhangxiaofanfan.book.groupchat.handler.PacketEncoder;
import com.zhangxiaofanfan.book.groupchat.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
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
 * 单聊系统: 客户端
 *
 * @date 2024-03-19 07:26:40
 * @author zhangxiaofanfan
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
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new QueryGroupResponseHandler());
                        ch.pipeline().addLast(new GroupMessageResponseHandler());
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
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        Scanner scanner = new Scanner(System.in);
        Thread chatThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    consoleCommandManager.exec(scanner, channel);
                } else {
                    loginConsoleCommand.exec(scanner, channel);
                }
            }
        });
        chatThread.start();
    }
}
