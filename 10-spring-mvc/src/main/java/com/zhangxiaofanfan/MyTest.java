package com.zhangxiaofanfan;

import com.zhangxiaofanfan.config.AnnotationConfig;
import com.zhangxiaofanfan.controller.HelloServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-30 上午11:41
 * @description TODO
 */
public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        HelloServlet helloServlet = context.getBean("helloServlet", HelloServlet.class);
        System.out.println(helloServlet.test());
    }
}
