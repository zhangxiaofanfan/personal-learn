package com.zhangxiaofanfan;

import com.zhangxiaofanfan.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring 容器学习使用类
 * 
 * @date 2023-07-06 20:12:40
 * @author zhangxiaofanfan
 */
public class Application {
    private final ApplicationContext context;

    public static void main(String[] args) {
        Application application = new Application();
        application.sayHello();
    }

    public Application() {
        context = new ClassPathXmlApplicationContext("beans.xml");
    }

    public void sayHello() {
        Hello hello = context.getBean("hello", Hello.class);
        System.out.println(hello.hello());
    }
}
