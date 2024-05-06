package com.zhangxiaofanfan.dubbo.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * dubbo consumer 主启动类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-09 10:17:27
 */
@EnableDubbo
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
