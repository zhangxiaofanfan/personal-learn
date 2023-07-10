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
    private final ClassPathXmlApplicationContext parentContext;
    private final ClassPathXmlApplicationContext childContext;

    public static void main(String[] args) {
        Application application = new Application();
        // application.sayHello();
        application.runTests();
    }

    public Application() {
        context = new ClassPathXmlApplicationContext("beans.xml");
        parentContext = new ClassPathXmlApplicationContext("parent-beans.xml");
        childContext = new ClassPathXmlApplicationContext(new String[] {"child-beans.xml"}, true, parentContext);
        parentContext.setId("ParentContext");
        childContext.setId("ChildContext");
    }

    public void runTests() {
        testVisibility(parentContext, "parentHello");
        testVisibility(childContext, "parentHello");
        testVisibility(parentContext, "childHello");
        testVisibility(childContext, "childHello");
        testOverridden(parentContext, "hello");
        testOverridden(childContext, "hello");
    }

    private void testVisibility(ApplicationContext context, String beanName) {
        System.out.println(context.getId() + " can see " + beanName + ": " + context.containsBean(beanName));
    }

    private void testOverridden(ApplicationContext context, String beanName) {
        System.out.println("sayHello from " + context.getId() + ": " + context.getBean(beanName, Hello.class).hello());
    }

    public void sayHello() {
        Hello hello = context.getBean("hello", Hello.class);
        System.out.println(hello.hello());
    }
}
