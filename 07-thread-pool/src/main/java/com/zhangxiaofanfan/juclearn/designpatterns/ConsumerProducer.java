package com.zhangxiaofanfan.juclearn.designpatterns;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者 消费者demo
 *
 * @author zhangxiaofanfan
 * @date 2024-01-02 14:30:55
 */
@Slf4j
public class ConsumerProducer {
    private static final AtomicInteger PRODUCER_COUNTER = new AtomicInteger(0);
    private static final AtomicInteger CONSUMER_COUNTER = new AtomicInteger(0);

    public static void main(String[] args) {
        MessageQueue<String> deque = new MessageQueue<>(5);
        for (int i = 0; i < 3; i++) {
            producerMessage(deque);
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            log.info("请输入后续操作: ");
            String operation = scanner.nextLine();
            if ("quit".equals(operation)) {
                break;
            }
            if (operation.equals("c") || "consumer".equals(operation)) {
                // 消费消息
                consumerMessage(deque);
            } else if (operation.equals("p") || "producer".equals(operation)) {
                // 生产消息
                producerMessage(deque);
            } else {
                log.info("无法识别指令, 现阶段支持指令为: [quit, c/consumer, p/producer]");
            }
        }
        deque.snapshot();
        log.info("执行结束...");
    }

    private static void producerMessage(MessageQueue<String> deque) {
        int count = PRODUCER_COUNTER.getAndIncrement();
        Producer<String> producer = new Producer<>(deque, new Message<>(count, "消息内容" + count));
        producer.setName("生产者线程-" + count);
        producer.start();
    }

    private static void consumerMessage(MessageQueue<String> deque) {
        int count = CONSUMER_COUNTER.getAndIncrement();
        Consumer<String> consumer = new Consumer<>(deque);
        consumer.setName("消费者线程-" + count);
        consumer.start();
    }
}

/**
 * 生产者类: 生产消息之后添加到消息队列的尾部
 */
@Slf4j
class Producer<T> extends Thread {
    private final Message<T> message;
    private final MessageQueue<T> messageQueue;

    public Producer(MessageQueue<T> deque, Message<T> message) {
        this.message = message;
        this.messageQueue = deque;
    }

    @Override
    public void run() {
        // 生产消息
        this.messageQueue.productMessage(this.message);
        this.messageQueue.snapshot();
    }
}

/**
 * 消费者类: 消费消息队列队首的消息
 */
@Slf4j
class Consumer<T> extends Thread {
    private final MessageQueue<T> messageQueue;

    public Consumer(MessageQueue<T> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        Message<T> consumerMessage = this.messageQueue.consumeMessage();
        log.info("消费数据: {}", consumerMessage);
        this.messageQueue.snapshot();
    }
}

@Slf4j
class MessageQueue<T> {
    private final Deque<Message<T>> deque;
    private final Integer capacity;

    public MessageQueue(Integer capacity) {
        this.capacity = capacity;
        this.deque = new ArrayDeque<>(capacity);
    }

    /**
     * 从消息队列的头部获取消息内容进行消费
     *
     * @return  返回头部结点数据
     */
    public synchronized Message<T> consumeMessage() {
        while (this.deque.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Message<T> consumer = this.deque.removeFirst();
        this.notifyAll();
        return consumer;

    }

    /**
     * 从消息队列的尾部追加消息
     *
     * @param message   生产之后需要添加到消息队列尾部的消息
     */
    public synchronized void productMessage(Message<T> message) {
        while (this.deque.size() >= capacity) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.deque.addLast(message);
        this.notifyAll();
    }

    public synchronized void snapshot() {
        log.info("消息队列大小: {}, 主键id列表为: {}", this.deque.size(), Arrays.toString(this.deque.stream().map(Message::getId).toArray()));
    }
}

/**
 * 封装了消息内容
 */
@ToString
final class Message<T> {
    @Getter
    private final Integer id;
    private final T content;

    public Message(Integer id, T content) {
        this.id = id;
        this.content = content;
    }
}
