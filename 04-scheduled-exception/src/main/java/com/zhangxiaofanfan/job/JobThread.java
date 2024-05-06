package com.zhangxiaofanfan.job;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步执行
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 19:28:22
 */
// @Slf4j
@Component
public class JobThread {
    @Async("asyncTaskExecutor")
    public CompletableFuture<Integer> run() {
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 模拟任务过程中出现异常
        int i = 1 / 0;
        return CompletableFuture.completedFuture(0);
    }
}
