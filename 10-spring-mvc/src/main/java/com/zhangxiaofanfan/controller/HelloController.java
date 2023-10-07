package com.zhangxiaofanfan.controller;

import com.zhangxiaofanfan.annotation.RequestMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午3:04
 * @description TODO
 */
@Component
public class HelloController {

    @RequestMapping("/test")
    public String handler01() {
        System.out.println("HelloController.handler01 working...");
        return "test";
    }
}
