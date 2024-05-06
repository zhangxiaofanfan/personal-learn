package com.zhangxiaofanfan.springboot.learn.config;

import com.zhangxiaofanfan.springboot.learn.bean.Bar;
import com.zhangxiaofanfan.springboot.learn.conditional.ExistBossConditional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;



/**
 * 吧台配置类
 *
 * @author zhangxiaofanfan
 * @date 2024-01-08 19:17:43
 */
@Configuration
public class BarConfiguration {
    @Bean
    @Conditional(ExistBossConditional.class)
    public Bar bbbar() {
        return new Bar();
    }
}
