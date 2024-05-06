package com.zhangxiaofanfan.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-06 14:37:40
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index1() {
        System.out.println("index1 被执行了...");
        return "index1";
    }
}
