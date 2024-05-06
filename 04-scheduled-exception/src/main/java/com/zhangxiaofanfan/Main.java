package com.zhangxiaofanfan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 异步任务异常没有正常返回造成进度丢失异常
 *
 * @date 2023-07-18 03:29:20
 * @author zhangxiaofanfan
 */
@EnableAsync
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}