package com.zhangxiaofanfan.server;

import com.zhangxiaofanfan.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-31 上午11:14
 * @description 使用 netty 搭建的分布式事务管理器服务端
 */
public class NettyServer {
    public void start(String hostName, Integer port){
        final ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.group(boss, worker);
        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast("decoder", new StringDecoder());
                ch.pipeline().addLast("encoder", new StringEncoder());
                ch.pipeline().addLast("handler", new ServerHandler());
            }
        });
    }
}
