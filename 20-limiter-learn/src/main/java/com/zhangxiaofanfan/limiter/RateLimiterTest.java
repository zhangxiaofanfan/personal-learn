package com.zhangxiaofanfan.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2023-12-12 10:26:57
 */
public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        // qps 2
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
            System.out.println(time + ":" + rateLimiter.tryAcquire());
            Thread.sleep(250);
        }
    }
}
