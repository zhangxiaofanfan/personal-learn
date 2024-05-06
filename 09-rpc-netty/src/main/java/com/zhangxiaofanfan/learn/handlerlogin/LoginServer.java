package com.zhangxiaofanfan.learn.handlerlogin;

import com.zhangxiaofanfan.learn.handlerlogin.handler.LoginRequestHandler;
import com.zhangxiaofanfan.learn.handlerlogin.handler.MessageRequestHandler;
import com.zhangxiaofanfan.learn.handlerlogin.handler.PacketDecoder;
import com.zhangxiaofanfan.learn.handlerlogin.handler.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 使用 netty 实现 登录服务端
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 10:15:37
 */
@Slf4j
public class LoginServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new PacketDecoder());
	                    ch.pipeline().addLast(new LoginRequestHandler());
	                    ch.pipeline().addLast(new MessageRequestHandler());
	                    ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
