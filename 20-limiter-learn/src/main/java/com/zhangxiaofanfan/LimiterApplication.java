package com.zhangxiaofanfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 限流测试启动类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-12 10:36:48
 */
@SpringBootApplication
public class LimiterApplication {
    public static void main(String[] args) {
        SpringApplication.run(LimiterApplication.class, args);
    }
}
