package com.zhangxiaofanfan.config;

import com.zhangxiaofanfan.pojo.Hello;
import com.zhangxiaofanfan.postprocessor.HelloPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 以注解的形式向容器中注入 Bean 对象
 *
 * @author zhangxiaofanfan
 * @date 2023-07-25 09:03:41
 */
@Configuration
public class Config {
    @Bean
    public Hello hello() {
        return new Hello();
    }

    @Bean
    public HelloPostProcessor postProcessor() {
        return new HelloPostProcessor();
    }
}
