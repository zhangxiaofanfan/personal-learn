package com.zhangxiaofanfan.cloud.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import java.time.Duration;

/**
 *
 *
 * @author zhangxiaofanfan
 * @date 2024-05-28 09:59:43
 */
@RestController
public class DemoController {
    @GetMapping("index")
    public String index(@RequestParam(name = "key", required = false, defaultValue = "zxff") String key) {
        return "index, key: " + key;
    }

    // webflux 建议返回结果进行 Mono Flux 封装后再返回
    @GetMapping("/haha")
    public Mono<String> haha() {
        return Mono.just("哈哈");
    }

    @GetMapping("/hehe")
    public Flux<String> hehe() {
        return Flux.just("哈哈1", "哈哈2");
    }

    // 配合上 sse 搭配使用
    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sse() {
        return Flux.range(1, 10)
                .map(value -> "哈哈" + value)
                .delayElements(Duration.ofMillis(500));
    }
}
