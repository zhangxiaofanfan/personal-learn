package com.zhangxiaofanfan.springboot.learn.config;

import com.zhangxiaofanfan.springboot.learn.bean.Bartender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 调酒师注册类, 向容器中注册多个调酒师对象
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 14:45:53
 */
@Configuration
@Profile(value = "city")
public class BartenderConfiguration {

    @Bean
    public Bartender bartender1() {
        return new Bartender("张小帆帆");
    }

    @Bean
    public Bartender bartender2() {
        return new Bartender("杨小棉棉");
    }
}
