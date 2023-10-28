package com.zhangxiaofanfan.consumer;

import com.zhangxiaofanfan.pojo.Company;
import com.zhangxiaofanfan.utils.KafkaUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 消费者: 从 kafka 中拉取 Company 对象
 *
 * @author zhangxiaofanfan
 * @date 2023-10-28 21:58:32
 */
public class CompanyConsumerStart {
    public static final String groupId = "group.demo";
    public static final String quitStr = "quit";
    public static void main(String[] args) {
        boolean isQuit = false;
        Properties properties = KafkaUtils.getCompanyConsumerProperties();
        // 设置消费组的名称
        properties.put("group.id", groupId);
        // 设置一个消费者客户端实例
        KafkaConsumer<String, Company> consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(KafkaUtils.topic));
        // 循环消费消息
        while (!isQuit) {
            ConsumerRecords<String, Company> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Company> record : records) {
                if (quitStr.equals(record.value().getName())) {
                    isQuit = true;
                } else {
                    System.out.println(record.value().toString());
                }
            }
        }
        consumer.close();
    }
}
