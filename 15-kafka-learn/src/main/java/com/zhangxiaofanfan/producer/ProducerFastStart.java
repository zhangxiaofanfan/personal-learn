package com.zhangxiaofanfan.producer;

import com.zhangxiaofanfan.utils.KafkaUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Kafka 学习过程中的生产者
 *
 * @author zhangxiaofanfan
 * @date 2023-10-16 15:32:26
 */
public class ProducerFastStart {

    public static void main(String[] args) {
        // 配置需要连接的 kafka 节点信息
        Properties properties = KafkaUtils.getKafkaProducerProperties();
        // 配置生产者客户端工具参数并创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        // 构建所需要发送的消息
        ProducerRecord<String, String> record = new ProducerRecord<>(KafkaUtils.topic, "Hello World by java programmer!");
        System.out.println("send msg begin......");
        // 发送消息
        try {
            producer.send(record);
        } catch (Exception e) {
            System.out.printf("error msg is %s", e.getMessage());
        }
        System.out.println("send msg end......");
        // 关闭生产者客户端示例
        producer.close();
    }
}
