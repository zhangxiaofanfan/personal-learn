package com.zhangxiaofanfan.cloud.gateway.filter.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 设置跨域 Filter
 *
 * @date 2024-04-22 09:32:55
 * @author zhangxiaofanfan
 */
@Slf4j
// @Component
public class CorsFilter implements WebFilter {

    private static final String ALL = "*";
    private static final String MAX_AGE = "3600L";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("过滤器[{}]执行了...", CorsFilter.class.getName());
        // 非跨域请求，直接放行
        ServerHttpRequest request = exchange.getRequest();
        if (!CorsUtils.isCorsRequest(request)) {
            return chain.filter(exchange);
        }

        // 设置跨域响应头
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.add("Access-Control-Allow-Origin", ALL);
        headers.add("Access-Control-Allow-Methods", ALL);
        headers.add("Access-Control-Allow-Headers", ALL);
        headers.add("Access-Control-Max-Age", MAX_AGE);
        if (request.getMethod() == HttpMethod.OPTIONS) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }
        return chain.filter(exchange);
    }

}
