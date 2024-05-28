package com.zhangxiaofanfan.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 通过 SpringBoot 启动 WebFlux 应用, 引入 spring-boot-starter-webflux 依赖即可
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 09:58:49
 */
@SpringBootApplication
public class WebFluxSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebFluxSpringBootApplication.class, args);
    }
}
