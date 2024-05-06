package com.zhangxiaofanfan.book.demo.server;

import com.zhangxiaofanfan.book.demo.handler.AuthHandler;
import com.zhangxiaofanfan.book.demo.handler.LoginRequestHandler;
import com.zhangxiaofanfan.book.demo.handler.MessageRequestHandler;
import com.zhangxiaofanfan.book.demo.handler.PacketDecoder;
import com.zhangxiaofanfan.book.demo.handler.PacketEncoder;
import com.zhangxiaofanfan.book.demo.handler.Spliter;
import com.zhangxiaofanfan.book.demo.server.handler.FirstServerHandler;
import com.zhangxiaofanfan.book.demo.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 使用 netty 实现简易服务端
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
                        // ch.pipeline().addLast(new FirstServerHandler());
                        // ch.pipeline().addLast(new ServerHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
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
