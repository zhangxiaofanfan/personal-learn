package com.zhangxiaofanfan.controller;

import com.zhangxiaofanfan.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 测试 async 使用的控制器类
 *
 * @author zhangxiaofanfan
 * @date 2023-10-26 20:08:34
 */
@Slf4j
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("index1")
    public String index1() {
        Future<String> stringFuture = indexService.index1();
        String result = "";
        log.info("future 对象获取结果之前..., 时间为: {}", new Date());
        try {
            result = stringFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        log.info("future 对象获取结果之后..., 时间为: {}", new Date());
        return result;
    }

    @GetMapping("index2")
    public String index2() {
        return indexService.index2();
    }
}
