package com.zhangxiaofanfan.config;

import com.zhangxiaofanfan.controller.HelloServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-30 上午10:32
 * @description TODO
 */
@Configuration
@ComponentScan("com.zhangxiaofanfan")
public class AnnotationConfig {

     @Bean("/helloServlet")
     public HelloServlet helloServlet() {
         return new HelloServlet();
     }

//     @Bean
//     public TestServlet testServlet() {
//         return new TestServlet();
//     }
}
