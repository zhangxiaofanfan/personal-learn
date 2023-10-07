package com.zhangxiaofanfan.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 测试 ApplicationRunner 注解使用
 * 
 * @date 2023-09-18 14:58:57
 * @author zhangxiaofanfan
 */
@Component
@Slf4j
public class ApplicationRunnerTest implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner running ...");
    }   
}
