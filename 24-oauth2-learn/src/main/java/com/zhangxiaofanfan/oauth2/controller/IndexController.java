package com.zhangxiaofanfan.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页控制器类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-01 10:34:39
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
