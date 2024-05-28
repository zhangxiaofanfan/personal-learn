package com.zhangxiaofanfan.cloud.reactor.generate;

import reactor.core.publisher.Flux;

/**
 * 创建流的方法演示方法
 *
 * @author zhangxiaofanfan
 * @date 2024-05-27 15:19:10
 */
public class GenerateDemo {
    public static void main(String[] args) {
        Flux.generate(
                () -> 0,
                (state, sink) -> {
                    sink.next(String.format("哈哈-%s", state));
                    if (state >= 10) {
                        sink.complete();
                    }
                    return state + 1;
                }
        ).subscribe(System.out::println);
    }
}
