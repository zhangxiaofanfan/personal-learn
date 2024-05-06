package com.zhangxiaofanfan.ws.server;

import com.zhangxiaofanfan.ws.server.initializer.WebSocketServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 启动 netty 服务, 并开启端口监听
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 09:51:01
 */
@Slf4j
@Component
public class WebSocketServer {

    /**
     * Netty 服务的端口号
     */
    @Value("${websocket.netty.port:19999}")
    public int port;

    @Value("${websocket.netty.path:/websocket}")
    public String webSocketPath;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    /**
     * 启动 WebSocket 服务器
     */
    private void start() throws InterruptedException {
        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        Channel channel = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WebSocketServerInitializer(webSocketPath))
                .bind(port)
                .sync()
                .channel();

        log.info("线程[{}]启动netty绑定端口[{}]成功!", Thread.currentThread().getName(), port);

        // 给 channel 注册一个关闭 future 事件, 通过同步方式, 等待连接关闭
        channel.closeFuture().sync();
    }

    /**
     * 释放资源
     * <p>
     * PreDestroy注解：在容器销毁该组件之前被调用
     * 注解使用前提：该类的实例必须是由容器创建和管理的，如 Spring、JavaEE 容器等。
     */
    @PreDestroy
    public void destroy() {
        Optional.ofNullable(bossGroup).ifPresent(EventLoopGroup::shutdownGracefully);
        Optional.ofNullable(workerGroup).ifPresent(EventLoopGroup::shutdownGracefully);
    }

    /**
     * 初始化WebSocketServer, 调用init()
     * PostConstruct注解：用于指示一个方法在容器创建该组件之后立即调用
     * 注解使用前提：该类的实例必须是由容器创建和管理的，如 Spring、JavaEE 容器等。
     */
    @PostConstruct
    public void init() {
        //这里要新开一个线程，否则会阻塞原本的controller等业务
        new Thread(() -> {
            try {
                start();
            } catch (InterruptedException e) {
                log.error("线程[{}]启动netty绑定端口[{}]失败, 失败原因: {}", Thread.currentThread().getName(), port, e.getMessage());
            }
        }, "netty-thread-main").start();
    }
}

