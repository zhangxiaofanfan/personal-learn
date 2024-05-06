package com.zhangxiaofanfan.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 学习 SpringBoot Admin 框架启动类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-06 12:38:50
 */
@EnableAdminServer
@SpringBootApplication
public class SpringBootAdmin {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdmin.class, args);
    }
}
