package com.zhangxiaofanfan.test;

import com.zhangxiaofanfan.spring.MyApplicationContext;
import com.zhangxiaofanfan.spring.annotation.ComponentScan;
import com.zhangxiaofanfan.test.service.UserService;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 上午10:07
 * @description Bean 实例化过程
 *  class --> 实例化 --> 对象 --> 属性填充 --> 初始化(afterPropertiesSet()之后执行) --> AOP --> 代理对象 --> Bean对象
 *  如果 Bean 对象没有 AOP 功能，则返回的是 class 创建出来的对象，如果具有 AOP 功能，返回的是代理后的对象
 */
@ComponentScan("com.zhangxiaofanfan.test.service")
public class MainTest {
    public static void main(String[] args) throws Exception {
        MyApplicationContext context = new MyApplicationContext(MainTest.class);

        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.indexService);
    }
}
