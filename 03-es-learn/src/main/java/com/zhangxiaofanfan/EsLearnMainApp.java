package com.zhangxiaofanfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 学习 ES 使用的模块启动类
 *
 * @date 2024-04-10 09:31:46
 * @author zhangxiaofanfan
 */
@RestController
@SpringBootApplication
public class EsLearnMainApp {
    public static void main(String[] args) {
        SpringApplication.run(EsLearnMainApp.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}