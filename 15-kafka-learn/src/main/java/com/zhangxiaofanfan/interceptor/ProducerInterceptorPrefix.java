package com.zhangxiaofanfan.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 生产者拦截器: 为消息添加前缀"prefix-1-" 并统计 消息发送成功率
 *
 * @author zhangxiaofanfan
 * @date 2023-10-29 12:39:29
 */
@Slf4j
public class ProducerInterceptorPrefix implements ProducerInterceptor<String, String> {
    private static final String PREFIX = "prefix-1-";
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    /**
     * 触发时机: KafkaProducer 再将消息序列化和计算分区之前会调用该方法来对消息进行相应的定制化操作
     * demo 功能: 发送前完成前缀字符的拼接
     *
     * @param record 拦截之后需要进行操作的 record 对象
     * @return  返回完成拦截操作的 record 对象
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String modifiedValue = PREFIX + record.value();
        return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(),
                modifiedValue, record.headers());

    }


    /**
     * 触发时机: 消息被应答之前或消息发送失败时调用该方法, 优先级高于用户设定的 CallBack 函数;
     * demo功能: 统计向 kafka 发送消息的成功和失败数据
     *
     * @param metadata 发送成功则不为 null
     * @param exception 发送失败不为 null
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (metadata != null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }

    /**
     * 触发时机: 关闭拦截器时执行一些资源的清理操作
     * demo功能: 在发送完成消息之后统计发送成功率
     */
    @Override
    public void close() {
        double successRatio = sendSuccess * 1.0 / (sendSuccess + sendFailure);
        System.out.printf("send success ratio is %s\n", successRatio);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
