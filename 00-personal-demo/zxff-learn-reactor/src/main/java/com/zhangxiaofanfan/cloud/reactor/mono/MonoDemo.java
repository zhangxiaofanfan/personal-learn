package com.zhangxiaofanfan.cloud.reactor.mono;

import reactor.core.publisher.Mono;

/**
 * Mono 表示存在 0|1 个数据的流, Mono 本身就是一个发布者: {@code Mono<T> implements CorePublisher<T>}
 *
 * @author zhangxiaofanfan
 * @date 2024-05-23 17:32:29
 */
public class MonoDemo {
    public static void main(String[] args) {
        // 1. 创建发布者, 创建多元素数据的流
        Mono<Integer> mono = Mono.just(1);
        // 2. 消费者发布数据, 订阅者消费数据
        mono.subscribe(ele -> System.out.println("element is " + ele));

    }
}
