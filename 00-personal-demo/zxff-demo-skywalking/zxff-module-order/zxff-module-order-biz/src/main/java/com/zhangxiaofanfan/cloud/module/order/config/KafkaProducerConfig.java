package com.zhangxiaofanfan.cloud.module.order.config;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 向实例中注入访问 kafka 生产者对象
 *
 * @author zhangxiaofanfan
 * @date 2024-04-30 13:42:33
 */
@Configuration
public class KafkaProducerConfig {

    // 创建kafka 操作模板对象, 用于简化消息发送操作
    @Bean
    public KafkaTemplate<?, ?> kafkaTemplate(ProducerFactory<Object, Object> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    // 创建kafka 生产者工厂
    @Bean
    public ProducerFactory<?, ?> producerFactory() {
        Map<String, Object> properties = buildProducerProperties();
        return new DefaultKafkaProducerFactory<>(properties);
    }


    /**
     * 构建生产者配置
     *
     * @return
     */
    public static Map<String, Object> buildProducerProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("bootstrap.servers", "10.24.20.101:8092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());
        return properties;
    }
}