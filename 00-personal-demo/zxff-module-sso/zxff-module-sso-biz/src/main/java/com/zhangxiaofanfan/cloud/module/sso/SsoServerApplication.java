package com.zhangxiaofanfan.cloud.module.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动 sso 单点登录服务使用的模块启动累
 *
 * @author zhangxiaofanfan
 * @date 2024-04-24 10:17:15
 */
@SpringBootApplication
public class SsoServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SsoServerApplication.class, args);
    }
}
