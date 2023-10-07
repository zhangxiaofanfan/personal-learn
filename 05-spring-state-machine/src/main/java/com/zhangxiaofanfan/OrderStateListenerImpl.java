package com.zhangxiaofanfan;

import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;

/**
 * 当 状态机 状态发生变化时, 绑定用来处理事件使用的对象
 *
 * @author zhangxiaofanfan
 * @date 2023-07-19 12:47:56
 */
@Component("orderStateListener")
@WithStateMachine(name = "orderStatusMachine")  // 绑定状态机
public class OrderStateListenerImpl {

    /**
     * 状态转换, 回调方法, 从待支付到待发货的状态转变
     *
     * @param message   封装了状态转换使用的通知消息
     * @return          回调方法执行是否成功
     * @throws InterruptedException
     */
    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    public boolean payTransition(Message<Order> message) {
        Order order = (Order)message.getHeaders().get("order");
        if (order == null) {
            System.out.println("支付过程中出现异常, 返回 false");
            return false;
        }
        order.setStatus(OrderStatus.WAIT_DELIVER);
        System.out.println("支付, 状态机反馈信息: " + message.getHeaders().toString());
        return true;
    }

    /**
     * 状态转换, 回调方法, 从待发货到待收货的状态转变
     *
     * @param message   封装了状态转换使用的通知消息
     * @return          回调方法执行是否成功
     * @throws InterruptedException
     */
    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    public boolean deliverTransition(Message<Order> message) throws InterruptedException {
        Order order = (Order)message.getHeaders().get("order");
        System.out.println("开始执行 deliver 动作......");
        Thread.sleep(2000);
        System.out.println("开始执行 deliver 动作......");
        if (order == null) {
            System.out.println("发货过程中出现异常, 返回 false");
            return false;
        }
        order.setStatus(OrderStatus.WAIT_RECEIVE);
        System.out.println("发货, 状态机反馈信息: " + message.getHeaders().toString());
        return true;
    }

    /**
     * 状态转换, 回调方法, 从待收货到订单完成的状态转变
     *
     * @param message   封装了状态转换使用的通知消息
     * @return          回调方法执行是否成功
     * @throws InterruptedException
     */
    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    public boolean receiveTransition(Message<Order> message) throws InterruptedException {
        Order order = (Order)message.getHeaders().get("order");
        System.out.println("开始执行 receive 动作......");
        Thread.sleep(2000);
        System.out.println("开始执行 receive 动作......");
        if (order == null) {
            System.out.println("收货过程中出现异常, 返回 false");
            return false;
        }
        order.setStatus(OrderStatus.FINISH);
        System.out.println("收货, 状态机反馈信息: " + message.getHeaders().toString());
        return true;
    }
}
