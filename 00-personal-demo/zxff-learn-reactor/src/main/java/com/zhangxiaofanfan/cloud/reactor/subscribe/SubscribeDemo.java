package com.zhangxiaofanfan.cloud.reactor.subscribe;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;
import reactor.util.annotation.NonNull;

import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/**
 * 订阅流演示 demo, 流被订阅之前, 无论添加多少中间 处理, 中间操作都不会执行
 *   响应式编程: 数据流 + 变化传播
 *
 * @author zhangxiaofanfan
 * @date 2024-05-24 09:33:17
 */
public class SubscribeDemo {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just(1, 2, 3, 4)
                .map(value -> ":哈哈" + value)
                .map(value -> {
                    if (value.contains("3")) {
                        throw new RuntimeException("抛出异常");
                    }
                    return value;
                });
                // .log();

        // 将消费者、异常处理消费者和完成时消费者注入到发布者订阅中, 实现所有消费的相关操作
        Consumer<String> consumer = System.out::println;
        Consumer<? super Throwable> errorConsumer = throwable -> System.out.println("异常消费者接收到数据, 异常数据为: " + throwable.getMessage());
        Runnable completeConsumer = () -> System.out.println("数据流处理完成");

        // flux.subscribe(System.out::println);
        // flux.subscribe(consumer, errorConsumer, completeConsumer);

        // 自定义订阅者, 可以通过钩子函数回调对应时期的消费操作
        Subscriber<String> mySubscriber = new BaseSubscriber<>() {

            // 生命周期钩子1: 订阅关系被绑定时触发
            @Override
            protected void hookOnSubscribe(@NonNull Subscription subscription) {
                System.out.println("订阅关系被绑定了");
                // 自定义订阅者要像发布者/数据缓冲区索取数据
                request(1); // 表示一次要 1/N 个数据
                requestUnbounded(); // 标识要无界的数据
            }

            @Override
            protected void hookOnNext(@NonNull String value) {
                System.out.println("数据到达了: " + value);
                request(1); // 表示一次要 1/N 个数据
            }

            @Override
            protected void hookFinally(@NonNull SignalType type) {
                System.out.println("数据流 finally 被执行了, 入参为: " + type);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("数据流被取消标识");
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("数据流正常结束标识");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                System.out.println("数据流正常异常标识, 异常信息: " + throwable.getMessage());
            }

        };
        flux.subscribe(mySubscriber);

        LockSupport.park();
    }
}
