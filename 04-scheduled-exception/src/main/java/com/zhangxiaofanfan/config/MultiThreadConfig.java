package com.zhangxiaofanfan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 向容器中注入线程池对象
 *
 * @author zhangxiaofanfan
 * @date 2024-01-31 19:23:36
 */
// @Slf4j
@Configuration
public class MultiThreadConfig {

    @Bean(name = "asyncTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数
        executor.setCorePoolSize(150);
        // 最大线程数
        executor.setMaxPoolSize(200);
        // 任务队列大小
        executor.setQueueCapacity(100);
        // 线程前缀名
        executor.setThreadNamePrefix("async-thread-");
        // 线程的空闲时间
        executor.setKeepAliveSeconds(100);
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 线程初始化
        executor.initialize();
        return executor;
    }

}
