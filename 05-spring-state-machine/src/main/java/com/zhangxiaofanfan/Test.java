package com.zhangxiaofanfan;

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
public class Test {
    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");
        ConfigurableApplicationContext context = SpringApplication.run(Test.class, args);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.create();
        orderService.create();
        orderService.pay(1);
        new Thread(() -> {
            orderService.deliver(1);
            orderService.receive(1);
        }, "客户线程").start();
        orderService.pay(2);
        orderService.deliver(2);
        orderService.receive(2);
        orderService.pay(1);
        orderService.deliver(1);
        orderService.receive(1);
        System.out.println("全部订单状态: " + orderService.getOrders());
    }
}
