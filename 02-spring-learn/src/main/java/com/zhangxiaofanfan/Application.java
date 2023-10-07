package com.zhangxiaofanfan;

import com.zhangxiaofanfan.config.Config;
import com.zhangxiaofanfan.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

/**
 * spring 容器学习使用类
 *
 * @author zhangxiaofanfan
 * @date 2023-07-06 20:12:40
 */
public class Application {
//    private final ApplicationContext contextXml;
    private final ApplicationContext contextConfig;
//    private final ClassPathXmlApplicationContext parentContext;
//    private final ClassPathXmlApplicationContext childContext;

    public static void main(String[] args) {
        Application application = new Application();
        // application.sayHello();
        // application.runTests();
        Hello hello = application.contextConfig.getBean(Hello.class);
        hello.setName("zhangxiaofanfan");
        System.out.println(hello.hello());
    }

    public Application() {
        // 1. 使用 xml 向容器中注入 Bean 对象
//        contextXml = new ClassPathXmlApplicationContext("beans.xml");
//        parentContext = new ClassPathXmlApplicationContext("parent-beans.xml");
//        childContext = new ClassPathXmlApplicationContext(new String[]{"child-beans.xml"}, true, parentContext);
//        parentContext.setId("ParentContext");
//        childContext.setId("ChildContext");

        // 2. 使用 注解 向容器中注入 Bean 对象
        contextConfig = new AnnotationConfigApplicationContext(Config.class);
    }

    public void runTests() {
//        testVisibility(parentContext, "parentHello");
//        testVisibility(childContext, "parentHello");
//        testVisibility(parentContext, "childHello");
//        testVisibility(childContext, "childHello");
//        testOverridden(parentContext, "hello");
//        testOverridden(childContext, "hello");
    }

    private void testVisibility(ApplicationContext context, String beanName) {
        System.out.println(context.getId() + " can see " + beanName + ": " + context.containsBean(beanName));
    }

    private void testOverridden(ApplicationContext context, String beanName) {
        System.out.println("sayHello from " + context.getId() + ": " + context.getBean(beanName, Hello.class).hello());
    }

    public void sayHello(ApplicationContext context) {
        Hello hello = context.getBean("hello", Hello.class);
        System.out.println(hello.hello());
    }
}
