package com.zhangxiaofanfan.cloud.gateway.filter.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * 解决 Spring Cloud Gateway 2.x 跨域时，出现重复 Origin 的 BUG:
 * <a href="https://blog.csdn.net/zimou5581/article/details/90043178">参考文档</a>
 *
 * @date 2024-04-22 09:33:14
 * @author zhangxiaofanfan
 */
@Slf4j
@Component
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 指定此过滤器位于 NettyWriteResponseFilter 之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("过滤器[{}]执行了...", CorsResponseHeaderFilter.class.getName());
        return chain.filter(exchange).then(Mono.defer(() -> {
            exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                    .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .forEach(kv -> kv.setValue(new ArrayList<>() {{
                        add(kv.getValue().getFirst());
                    }}));
            return chain.filter(exchange);
        }));
    }

}
