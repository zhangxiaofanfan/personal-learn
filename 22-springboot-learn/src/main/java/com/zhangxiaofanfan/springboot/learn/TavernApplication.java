package com.zhangxiaofanfan.springboot.learn;

import com.zhangxiaofanfan.springboot.learn.config.TavernConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * 酒馆启动类
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 12:40:24
 */
@Slf4j
public class TavernApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(TavernConfiguration.class);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(bean -> log.info("bean is {}", bean));
    }
}
