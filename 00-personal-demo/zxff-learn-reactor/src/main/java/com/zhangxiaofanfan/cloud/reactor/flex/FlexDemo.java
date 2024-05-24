package com.zhangxiaofanfan.cloud.reactor.flex;

import reactor.core.publisher.Flux;

/**
 * Flux 表示存在多个数据的流, Flux 本身就是一个发布者: {@code Flux<T> implements CorePublisher<T>}
 *
 * @author zhangxiaofanfan
 * @date 2024-05-23 17:14:04
 */
public class FlexDemo {
    public static void main(String[] args) {
        // 1. 创建发布者, 创建多元素数据的流
        Flux<Integer> flux = Flux
                .just(1, 2, 3, 4, 5)
                .doOnComplete(() -> System.out.println("flux 数据流执行完毕"));
        // 2. 消费者发布数据, 订阅者消费数据
        flux.subscribe(ele -> System.out.println("element is " + ele));
    }
}
