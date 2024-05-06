package com.zhangxiaofanfan.learn.chatroom.netty;

import com.zhangxiaofanfan.learn.chatroom.netty.handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * 使用 netty 实现的聊天室服务端
 *
 * @author zhangxiaofanfan
 * @date 2024-02-02 10:15:37
 */
@Slf4j
public class ChatRoomDemoServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        // boss对应IOServer.java中的负责接收新连接的线程，主要负责创建新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        // worker对应IOServer.java中的负责读取数据的线程，主要用于读取数据及业务逻辑处理。
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                log.info(msg);
                            }
                        });
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
        bind(serverBootstrap, 8080);
    }

    private static void bind(final ServerBootstrap bootstrap, final Integer port) {
        bootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    log.info("绑定端口成功, 端口号码: {}", port);
                } else {
                    log.info("绑定端口失败, 端口号码: {}", port);
                    bind(bootstrap, port + 1);
                }
            }
        });
    }
}
