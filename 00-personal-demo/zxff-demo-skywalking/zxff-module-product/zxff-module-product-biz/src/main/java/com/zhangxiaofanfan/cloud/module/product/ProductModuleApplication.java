package com.zhangxiaofanfan.cloud.module.product;

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
public class ProductModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductModuleApplication.class, args);
    }
}
