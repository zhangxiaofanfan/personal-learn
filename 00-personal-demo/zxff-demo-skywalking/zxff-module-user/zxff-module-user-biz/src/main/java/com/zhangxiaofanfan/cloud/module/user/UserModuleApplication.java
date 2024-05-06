package com.zhangxiaofanfan.cloud.module.user;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:36:35
 */
@Slf4j
@SpringBootApplication
public class UserModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserModuleApplication.class, args);
    }

    @Autowired
    ApplicationContext applicationContext;
    @PostConstruct
    public void getAllUrl() {
        applicationContext
                .getBean(RequestMappingHandlerMapping.class)
                .getHandlerMethods()
                .keySet()
                .forEach(i -> i.getPatternValues().forEach(log::info));
    }

}
