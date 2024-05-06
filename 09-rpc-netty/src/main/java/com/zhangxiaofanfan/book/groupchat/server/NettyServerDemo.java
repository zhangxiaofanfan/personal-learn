package com.zhangxiaofanfan.book.groupchat.server;

import com.zhangxiaofanfan.book.groupchat.server.handler.CreateGroupRequestHandler;
import com.zhangxiaofanfan.book.groupchat.server.handler.GroupMessageRequestHandler;
import com.zhangxiaofanfan.book.groupchat.server.handler.JoinGroupRequestHandler;
import com.zhangxiaofanfan.book.groupchat.handler.PacketDecoder;
import com.zhangxiaofanfan.book.groupchat.handler.Spliter;
import com.zhangxiaofanfan.book.groupchat.server.handler.LoginRequestHandler;
import com.zhangxiaofanfan.book.groupchat.server.handler.MessageRequestHandler;
import com.zhangxiaofanfan.book.groupchat.handler.PacketEncoder;
import com.zhangxiaofanfan.book.groupchat.server.handler.QueryGroupRequestHandler;
import com.zhangxiaofanfan.book.groupchat.server.handler.QuitGroupRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 群聊系统: 服务端
 *
 * @author zhangxiaofanfan
 * @date 2024-03-11 19:26:29
 */
public class NettyServerDemo {
    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup work = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        bootstrap
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        ch.pipeline().addLast(new QueryGroupRequestHandler());
                        ch.pipeline().addLast(new GroupMessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(bootstrap, 8000);
    }

    private static void bind(final ServerBootstrap bootstrap, final int port) {
        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.printf("端口[%s]绑定成功\n", port);
            } else {
                System.out.printf("端口[%s]绑定失败, 尝试绑定端口[%s]", port, port + 1);
                bind(bootstrap, port + 1);
            }
        });
    }
}
