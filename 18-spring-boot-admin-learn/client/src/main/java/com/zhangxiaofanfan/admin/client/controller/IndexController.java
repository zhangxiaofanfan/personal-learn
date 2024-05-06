package com.zhangxiaofanfan.admin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * index 测试类, 每次使用都会测试一个大对象
 *
 * @author zhangxiaofanfan
 * @date 2023-12-06 15:24:28
 */
@Slf4j
@RestController
public class IndexController {
    private final AtomicInteger count = new AtomicInteger(0);

    @GetMapping("index")
    private String index() {
        // 每次进来如打印下日志
        log.info("{} 啪...我第{}次进来了.", LocalDateTime.now(),  count.addAndGet(1));
        // 每次进来new 个大对象，便于监控观察堆内存变化
        byte[] bytes = new byte[100 * 1024 * 1024];
        log.info("new了 100MB");
        return "hi springboot admin " + LocalDateTime.now();
    }
}
