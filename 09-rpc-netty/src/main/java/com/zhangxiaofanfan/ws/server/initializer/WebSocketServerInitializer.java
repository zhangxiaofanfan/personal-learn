package com.zhangxiaofanfan.ws.server.initializer;

import com.zhangxiaofanfan.ws.handler.WebSocketIdleStateHandler;
import com.zhangxiaofanfan.ws.handler.WebSocketTextHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 定义 channel 连接需要进行处理的 handler 集合
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:23:10
 */
@Slf4j
@AllArgsConstructor
public class WebSocketServerInitializer extends ChannelInitializer<NioSocketChannel> {

    /**
     * WebSocket 服务的接口地址
     */
    public String webSocketPath;


    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {
        log.info("netty服务的接口地址：{}", webSocketPath);
        ChannelPipeline pipeline = ch.pipeline();
        // 自定义的Handler-心跳检测
        pipeline.addLast(new WebSocketIdleStateHandler());
        // HTTP协议编解码器，用于处理HTTP请求和响应的编码和解码。其主要作用是将HTTP请求和响应消息转换为Netty的ByteBuf对象，并将其传递到下一个处理器进行处理。
        pipeline.addLast(new HttpServerCodec());
        // 用于HTTP服务端，将来自客户端的HTTP请求和响应消息聚合成一个完整的消息，以便后续的处理。
        pipeline.addLast(new HttpObjectAggregator(65536));
        // 用于对 WebSocket 消息进行压缩和解压缩操作。
        pipeline.addLast(new WebSocketServerCompressionHandler());
        // 可以对整个 WebSocket 通信进行初始化（当Http请求中有升级为WebSocket的请求时），以及握手处理
        pipeline.addLast(new WebSocketServerProtocolHandler(webSocketPath, null, true));
        // 自定义的 Handler 处理WebSocket文本类型的消息
        pipeline.addLast(new WebSocketTextHandler());
    }

}
