package com.zhangxiaofanfan.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot + netty 实现 websocket 服务
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 09:45:05
 */
@SpringBootApplication
public class SpringBootNettyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootNettyApplication.class, args);
    }
}
