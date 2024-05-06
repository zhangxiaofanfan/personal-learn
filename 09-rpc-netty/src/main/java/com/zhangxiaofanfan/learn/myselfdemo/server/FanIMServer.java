package com.zhangxiaofanfan.learn.myselfdemo.server;

import com.zhangxiaofanfan.learn.myselfdemo.codec.DivisionDecode;
import com.zhangxiaofanfan.learn.myselfdemo.codec.PacketCodec;
import com.zhangxiaofanfan.learn.myselfdemo.server.handler.LoginRequestHandler;
import com.zhangxiaofanfan.learn.myselfdemo.server.handler.LogoutRequestHandler;
import com.zhangxiaofanfan.learn.myselfdemo.server.handler.MessageRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 使用 netty 自定义服务器实现
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 14:28:28
 */
@Slf4j
public class FanIMServer {
    /**
     * 设置服务器需要监听的端口号, 后续可以通过配置的方式完成动态加载
     */
    private static final Integer PORT = 9359;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new DivisionDecode());
                        ch.pipeline().addLast(new PacketCodec());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new LogoutRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                    }
                });
        bind(serverBootstrap);
    }

    private static void bind(final ServerBootstrap serverBootstrap) {
        serverBootstrap.bind(FanIMServer.PORT).addListener(future -> {
            if (future.isSuccess()) {
                log.info("{}: 端口[{}]绑定成功!", new Date(), FanIMServer.PORT);
            } else {
                log.error("端口[{}]绑定失败!", FanIMServer.PORT);
            }
        });
    }
}
