package com.zhangxiaofanfan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器测试类
 * 
 * @date 2023-09-18 15:02:22
 * @author zhangxiaofanfan
 */
@RestController
public class IndexController {
    @GetMapping("index")
    public String index() {
        return "index";
    }
}
