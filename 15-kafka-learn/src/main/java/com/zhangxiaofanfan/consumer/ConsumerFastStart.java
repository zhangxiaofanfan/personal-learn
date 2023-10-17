package com.zhangxiaofanfan.consumer;

import com.zhangxiaofanfan.utils.KafkaUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Kafka 学习过程中的 消费者
 *
 * @author zhangxiaofanfan
 * @date 2023-10-17 14:45:32
 */
public class ConsumerFastStart {
    public static final String groupId = "group.demo";
    public static final String quitStr = "quit";
    public static void main(String[] args) {
        boolean isQuit = false;
        Properties properties = KafkaUtils.getKafkaConsumerProperties();
        // 设置消费组的名称
        properties.put("group.id", groupId);
        // 设置一个消费者客户端实例
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(KafkaUtils.topic));
        // 循环消费消息
        while (!isQuit) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                if (quitStr.equals(record.value())) {
                    isQuit = true;
                } else {
                    System.out.println(record.value());
                }
            }
        }
        consumer.close();
    }
}
