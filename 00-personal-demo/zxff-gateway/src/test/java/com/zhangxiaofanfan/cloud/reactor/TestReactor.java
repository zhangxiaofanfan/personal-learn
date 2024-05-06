package com.zhangxiaofanfan.cloud.reactor;

import com.zhangxiaofanfan.cloud.reactor.pojo.ClientUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 学习响应式编程 Reactor 使用的测试类
 *  1. flux: 不断变化的
 *
 * @author zhangxiaofanfan
 * @date 2024-04-22 09:55:53
 */
public class TestReactor {
    public static void main(String[] args) {
        // fluxUseLearn01();
        MonoUseLearn01();
    }

    /**
     * Flux 学习使用
     */
    private static void fluxUseLearn01() {
        //just()：创建Flux序列，并声明数据流，
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);//整形
        //subscribe()：订阅Flux序列，只有进行订阅后才回触发数据流，不订阅就什么都不会发生
        integerFlux.subscribe(System.out::println);

        Flux<String> stringFlux = Flux.just("hello", "world");//字符串
        stringFlux.subscribe(System.out::println);

        //fromArray(),fromIterable()和fromStream()：可以从一个数组、Iterable 对象或Stream 对象中创建Flux序列
        Integer[] array = {1, 2, 3, 4};
        Flux.fromArray(array).subscribe(System.out::println);

        List<Integer> integers = Arrays.asList(array);
        Flux.fromIterable(integers).subscribe(System.out::println);

        Stream<Integer> stream = integers.stream();
        Flux.fromStream(stream).subscribe(System.out::println);
    }

    private static void MonoUseLearn01() {
        Mono<String> data = Mono.just("bole");
        Mono<Object> noData = Mono.empty();
        data.subscribe(System.out::println);
    }
}
