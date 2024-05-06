package com.zhangxiaofanfan.book.singlechat.client;

import com.zhangxiaofanfan.book.singlechat.handler.LoginResponseHandler;
import com.zhangxiaofanfan.book.singlechat.handler.MessageResponseHandler;
import com.zhangxiaofanfan.book.singlechat.handler.PacketDecoder;
import com.zhangxiaofanfan.book.singlechat.handler.PacketEncoder;
import com.zhangxiaofanfan.book.singlechat.packet.pojo.LoginRequestPacket;
import com.zhangxiaofanfan.book.singlechat.packet.pojo.MessageRequestPacket;
import com.zhangxiaofanfan.book.singlechat.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.logging.log4j.util.Strings;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
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
        Thread chatThread = new Thread(() -> {
            while (!Thread.interrupted()) {
                if (SessionUtil.hasLogin(channel)) {
                    System.out.println("请输入聊天内容, 按回车键结束");
                    // 完成登录之后, 可以进行聊天
                    System.out.print("> ");
                    String[] split = scanner.nextLine().split(":");
                    MessageRequestPacket message = MessageRequestPacket
                            .builder()
                            .toUserId(split[0])
                            .message(split[1])
                            .build();
                    channel.writeAndFlush(message);
                } else {
                    // 没有完成登录, 进行登录设置
                    String loginString = getUserConsoleLoginString(scanner);
                    String[] loginInfo = loginString.split("-");
                    LoginRequestPacket login = LoginRequestPacket
                            .builder()
                            .username(loginInfo[0])
                            .password(loginInfo[1])
                            .build();
                    channel.writeAndFlush(login);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        chatThread.start();
    }

    private static String getUserConsoleLoginString(Scanner scanner) {
        System.out.println("请先完成登录, 登录格式:【用户名-密码】, 登录后按回车结束");
        while (true) {
            System.out.print("> ");
            String loginString = scanner.nextLine();
            if (Strings.isNotBlank(loginString) && loginString.split("-").length == 2) {
                return loginString;
            }
            System.out.println("登录信息失败, 请重新登录");
        }
    }
}
