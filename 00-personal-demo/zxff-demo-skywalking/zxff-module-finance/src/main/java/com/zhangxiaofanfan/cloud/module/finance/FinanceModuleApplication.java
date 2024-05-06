package com.zhangxiaofanfan.cloud.module.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-30 13:56:07
 */
@EnableKafka
@SpringBootApplication
public class FinanceModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(FinanceModuleApplication.class, args);
    }
}
