package com.zhangxiaofanfan.controller;

import com.zhangxiaofanfan.job.JobThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 控制器类
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 19:44:16
 */
@RestController
public class IndexController {
    @Autowired
    private JobThread jobThread;

    @GetMapping("index")
    public String index() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> run = jobThread.run();
        run.get();
        return "index";
    }
}
