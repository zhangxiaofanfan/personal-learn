package com.zhangxiaofanfan.cloud.reactor.reshape;

import reactor.core.publisher.Flux;

/**
 * 请求重塑 demo 演示
 *  1. buffer 是将请求进行缓存操作, 之前一个一个的请求会按照 bufferSize 大小进行 List 包装, 将包装之后的流进行传递, 向下继续执行消费操作;
 *  2. limitRate(N): 第一次请求 N 个数据进行消费, 当消费完成 75% 数量时, 再次请求 75% 的数据, 之后每次都是请求 N * 75% 的数据进行消费, 直到请求完成所有数据;
 *
 * @author zhangxiaofanfan
 * @date 2024-05-27 11:08:07
 */
public class RequestReshapingDemo {
    public static void main(String[] args) {
        // buffer 操作演示 demo
        // bufferMethod();
        limitMethod();
    }

    private static void bufferMethod() {
        Flux.range(1, 10)
                .buffer(3)
                .subscribe(System.out::println);
    }

    private static void limitMethod() {
        Flux.range(1, 100)
                .log()
                .limitRate(20)
                .subscribe(System.out::println);
    }

}
