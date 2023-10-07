package com.zhangxiaofanfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 模拟定时任务异常被吞的现场
 *
 * @date 2023-07-18 03:29:20
 * @author zhangxiaofanfan
 */
@EnableScheduling
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}