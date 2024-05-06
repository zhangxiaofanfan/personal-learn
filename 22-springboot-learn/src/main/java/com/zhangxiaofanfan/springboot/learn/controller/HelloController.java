package com.zhangxiaofanfan.springboot.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 欢迎页控制器类实现
 *
 * @author zhangxiaofanfan
 * @date 2024-01-05 17:45:10
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello SpringBoot";
    }
}
