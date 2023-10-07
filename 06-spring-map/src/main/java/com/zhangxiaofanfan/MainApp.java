package com.zhangxiaofanfan;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 状态机测试类
 *
 * @author zhangxiaofanfan
 * @date 2023-07-19 12:48:55
 */
@SpringBootApplication
public class MainApp {
    @Autowired
    private Map<String, TestInterface> map;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainApp.class, args);
        MainApp bean = context.getBean(MainApp.class);
        bean.map.keySet().forEach(key -> System.out.println(key + ": " + bean.map.get(key).getClass().getName()));
    }
}
