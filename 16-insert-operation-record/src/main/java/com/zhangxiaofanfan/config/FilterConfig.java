package com.zhangxiaofanfan.config;

import com.zhangxiaofanfan.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 先容器中注入过滤器实用的配置类
 *
 * @author zhangxiaofanfan
 * @date 2023-10-24 17:10:22
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<UserFilter> userFilter() {
        FilterRegistrationBean<UserFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new UserFilter());
        registration.addUrlPatterns("/*");
        registration.setName("userFilter");
        registration.setOrder(1);
        return registration;
    }
}
