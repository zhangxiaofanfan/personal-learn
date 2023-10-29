package com.zhangxiaofanfan.utils;

import com.zhangxiaofanfan.interceptor.ProducerInterceptorPrefix;
import com.zhangxiaofanfan.serializer.CompanyDeserializer;
import com.zhangxiaofanfan.serializer.CompanySerializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
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
    public static final String brokerList = "localhost:8092";

    /**
     * 消息队列生产者使用的配置对象, 向消息队列发送 String 对象
     *
     * @return 配置对象
     */
    public static Properties getKafkaProducerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    /**
     * 消息队列生产者使用的配置对象, 向消息队列发送 Company 对象
     *
     * @return 配置对象
     */
    public static Properties getCompanyProducerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CompanySerializer.class.getName());
        return properties;
    }

    /**
     * 消息队列生产者使用的配置对象, 向消息队列发送 Company 对象
     *
     * @return 配置对象
     */
    public static Properties getInterceptorProducerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName());
        return properties;
    }

    /**
     * 消息队列消费者使用的配置对象, 从消息队列拉取 String 对象
     *
     * @return 配置对象
     */
    public static Properties getKafkaConsumerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return properties;
    }

    /**
     * 消息队列消费者使用的配置对象, 从消息队列拉取 Company 对象
     *
     * @return 配置对象
     */
    public static Properties getCompanyConsumerProperties() {
        // 配置需要连接的 kafka 节点信息
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CompanyDeserializer.class.getName());
        return properties;
    }
}
