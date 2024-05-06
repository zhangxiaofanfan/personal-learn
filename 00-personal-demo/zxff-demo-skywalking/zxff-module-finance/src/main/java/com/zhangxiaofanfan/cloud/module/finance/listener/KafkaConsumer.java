package com.zhangxiaofanfan.cloud.module.finance.listener;


import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.BatchConsumerAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @date 2024-04-30 01:59:47
 * @author zhangxiaofanfan
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "SW-ORDER-SERVICE", groupId = "SW-ORDER-SERVICE")
    public void listen(String message) {
        System.out.println("Received message in group myGroup: " + message);
    }
}
