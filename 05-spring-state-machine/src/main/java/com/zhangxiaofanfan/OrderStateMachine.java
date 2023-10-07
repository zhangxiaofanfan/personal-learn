package com.zhangxiaofanfan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.stereotype.Component;

/**
 * 向容器中注册状态机对象, 完成状态机时间发送
 *
 * @author zhangxiaofanfan
 * @date 2023-07-20 09:26:41
 */
@Component
public class OrderStateMachine {
    // @Autowired
    // private StateMachineFactory<OrderStatus, OrderStatusChangeEvent> orderMachineFactory;
    @Autowired
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
    @Autowired
    private DefaultStateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persist;

    public synchronized boolean sendEvent(Message<OrderStatusChangeEvent> message, Order order) {
        boolean result = false;
        // StateMachine<OrderStatus,OrderStatusChangeEvent> orderStateMachine = orderMachineFactory.getStateMachine(String.valueOf(order.getId()));
        try {
            orderStateMachine.start();
            persist.restore(orderStateMachine, order);
            Thread.sleep(1000);
            result = orderStateMachine.sendEvent(message);
            persist.persist(orderStateMachine, order);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }
}
