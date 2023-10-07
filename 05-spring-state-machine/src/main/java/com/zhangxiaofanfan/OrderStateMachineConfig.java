package com.zhangxiaofanfan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.support.DefaultStateMachineContext;

import java.util.EnumSet;

/**
 * 向容器中注入 状态机使用的工厂类 对象
 *
 * @author zhangxiaofanfan
 * @date 2023-07-19 12:48:13
 */
@Slf4j
@Configuration
@EnableStateMachine(name = "orderStatusMachine")
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatus, OrderStatusChangeEvent> {

    /**
     * 配置 初始 状态
     * @param states        封装包含订单状态和时间的对象
     * @throws Exception    过程中异常
     */
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderStatusChangeEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.WAIT_PAYMENT)
                .states(EnumSet.allOf(OrderStatus.class));
    }

    /**
     * 配置状态转移关系
     *
     * @param transitions   封装 状态转移相关 对象
     * @throws Exception    过程中异常
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderStatusChangeEvent> transitions) throws Exception {
        transitions
                .withExternal()
                    .source(OrderStatus.WAIT_PAYMENT).target(OrderStatus.WAIT_DELIVER)
                    .event(OrderStatusChangeEvent.PAYED)
                    .and()
                .withExternal()
                    .source(OrderStatus.WAIT_DELIVER).target(OrderStatus.WAIT_RECEIVE)
                    .event(OrderStatusChangeEvent.DELIVER)
                    .and()
                .withExternal()
                    .source(OrderStatus.WAIT_RECEIVE).target(OrderStatus.FINISH)
                    .event(OrderStatusChangeEvent.RECEIVED);
    }

    /**
     * 持久化操作使用对象
     *
     * @return  进行状态机吃就是使用的bean对象
     */
    @Bean
    public DefaultStateMachinePersister<OrderStatus, OrderStatusChangeEvent, Order> persist() {
        return new DefaultStateMachinePersister<>(new StateMachinePersist<OrderStatus, OrderStatusChangeEvent, Order>() {
            @Override
            public void write(StateMachineContext<OrderStatus, OrderStatusChangeEvent> context, Order contextObj) throws Exception {
                log.info("write running......");
            }

            @Override
            public StateMachineContext<OrderStatus, OrderStatusChangeEvent> read(Order contextObj) throws Exception {
                log.info("read running......");
                return null;
            }
        });
    }
}
