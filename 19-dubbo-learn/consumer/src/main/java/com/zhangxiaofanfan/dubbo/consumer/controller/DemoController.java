package com.zhangxiaofanfan.dubbo.consumer.controller;

import com.zhangxiaofanfan.duubo.commons.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器使用
 *
 * @author zhangxiaofanfan
 * @date 2023-12-09 10:11:25
 */
@RestController
public class DemoController {
    @DubboReference
    private DemoService demoService;

    @GetMapping("index")
    public String index() {
        return demoService.sayHello("zhangfan");
    }
}
