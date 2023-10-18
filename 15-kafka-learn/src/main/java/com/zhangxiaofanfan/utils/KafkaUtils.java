package com.zhangxiaofanfan.utils;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 封装操作 kafka 的公共方法
 *
 * @author zhangxiaofanfan
 * @date 2023-10-17 14:59:44
 */
public class KafkaUtils {
    public static final String topic = "topic-demo";
    public static final String brokerList = "localhost:9092";
    public static Properties getKafkaProducerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    public static Properties getKafkaConsumerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put("bootstrap.servers", brokerList);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
}
