package com.zhangxiaofanfan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单服务器实现类
 *
 * @author zhangxiaofanfan
 * @date 2023-07-19 12:47:28
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderStateMachine orderStateMachine;
    private Integer id = 1;
    private final Map<Integer, Order> orders = new HashMap<>();
    
    /**
     * 创建订单
     *
     * @return  返回新建的订单对象
     */
    @Override
    public Order create() {
        Order order = new Order();
        // 已下单(临时单), 等待支付状态
        order.setStatus(OrderStatus.WAIT_PAYMENT);
        order.setId(id++);
        orders.put(order.getId(), order);
        return order;
    }

    /**
     * 支付订单
     *
     * @param id    订单编号
     * @return      返回支付后的订单
     */
    @Override
    public Order pay(int id) {
        Order order = orders.get(id);
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 尝试支付, 订单编号: " + order.getId());
        // 构造消息
        Message<OrderStatusChangeEvent> msg = MessageBuilder
                                                .withPayload(OrderStatusChangeEvent.PAYED)
                                                .setHeader("order", order)
                                                .build();
        if (!orderStateMachine.sendEvent(msg, order)) {
            System.out.println("线程名称: " + Thread.currentThread().getName() + " 支付失败, 订单异常, 订单编号: " + order.getId());
        }
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 支付成功, 订单编号: " + order.getId());
        return orders.get(id);
    }

    @Override
    public Order deliver(int id) {
        Order order = orders.get(id);
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 尝试发货, 订单编号: " + order.getId());
        // 构造消息
        Message<OrderStatusChangeEvent> msg = MessageBuilder
                                                .withPayload(OrderStatusChangeEvent.DELIVER)
                                                .setHeader("order", order)
                                                .build();
        if (!orderStateMachine.sendEvent(msg, order)) {
            System.out.println("线程名称: " + Thread.currentThread().getName() + " 发货失败, 订单异常, 订单编号: " + order.getId());
        }
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 发货成功, 订单编号: " + order.getId());
        return orders.get(id);
    }

    @Override
    public Order receive(int id) {
        Order order = orders.get(id);
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 尝试收货, 订单编号: " + order.getId());
        // 构造消息
        Message<OrderStatusChangeEvent> msg = MessageBuilder
                                                .withPayload(OrderStatusChangeEvent.RECEIVED)
                                                .setHeader("order", order)
                                                .build();
        if (!orderStateMachine.sendEvent(msg, order)) {
            System.out.println("线程名称: " + Thread.currentThread().getName() + " 收货失败, 订单异常, 订单编号: " + order.getId());
        }
        System.out.println("线程名称: " + Thread.currentThread().getName() + " 收货 成功, 订单编号: " + order.getId());
        return orders.get(id);
    }

    @Override
    public Map<Integer, Order> getOrders() {
        return orders;
    }
}
