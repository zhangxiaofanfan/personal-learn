package com.zhangxiaofanfan.rpc;

import com.zhangxiaofanfan.rpc.handler.RpcRequestMessageHandler;
import com.zhangxiaofanfan.rpc.protocol.MessageCodecSharable;
import com.zhangxiaofanfan.rpc.protocol.ProtocolFrameDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-30 下午3:21
 * @description RPC 框架服务器端
 */
public class PpcServer {
    public static void main(String[] args) {
        // 服务器端启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();
        // boss 负责连接事件, worker 负责连接完成后的可读可写等事件
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        LoggingHandler LOGGING_HANDLER = new LoggingHandler(LogLevel.DEBUG);
        MessageCodecSharable MESSAGE_HANDLER = new MessageCodecSharable();
        RpcRequestMessageHandler RPC_REQUEST_HANDLER = new RpcRequestMessageHandler();

        // 1. 服务器端设置处理事件的线程对象
        bootstrap.group(boss, worker);
        // 2. 设置通道类型为 NioServerSocketChannel 类型
        bootstrap.channel(NioServerSocketChannel.class);
        // 3. 处理请求而增加的 HANDLER
        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                // 定义帧获取网络通信报文
                ch.pipeline().addLast("PROTOCOL_HANDLER", new ProtocolFrameDecoder());
                // 日志记录处理器
                ch.pipeline().addLast("LOGGING_HANDLER", LOGGING_HANDLER);
                // 消息编/解码器
                ch.pipeline().addLast("MESSAGE_HANDLER", MESSAGE_HANDLER);
                // Rpc Request 请求处理器
                ch.pipeline().addLast("RPC_REQUEST_HANDLER", RPC_REQUEST_HANDLER);
            }
        });

        // 通过启动器绑定端口并启动
        try {
            Channel channel = bootstrap.bind(8001).sync().channel();
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
