package com.zhangxiaofanfan.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.locks.LockSupport;

/**
 * 学习 web flux 自建的启动类
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 09:20:23
 */
@Slf4j
public class WebFluxApplication {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8080;

        HttpHandler handler = (request, response) -> {
            log.info("服务器请求进来了, 请求uri: {}", request.getURI());
            // 将请求 URI 拼接上 zxff 后进行返回
            URI uri = request.getURI();
            DataBufferFactory bufferFactory = response.bufferFactory();
            return response.writeWith(Mono.just(bufferFactory.wrap((uri + "zxff").getBytes(StandardCharsets.UTF_8))));
        };

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
        HttpServer
                .create()
                .host(host)
                .port(port)
                .handle(adapter)
                .bindNow();
        log.info("netty 服务器启动成功");
        LockSupport.park();
        log.info("netty 服务器停止成功");
    }
}
