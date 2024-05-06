package com.zhangxiaofanfan.cloud.framework.ratelimiter.config;

import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.aop.RateLimiterAspect;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.keyresolver.RateLimiterKeyResolver;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.redis.RateLimiterRedisDAO;
import com.zhangxiaofanfan.cloud.framework.redis.config.ZxffRedisAutoConfiguration;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.keyresolver.impl.ClientIpRateLimiterKeyResolver;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.keyresolver.impl.DefaultRateLimiterKeyResolver;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.keyresolver.impl.ExpressionRateLimiterKeyResolver;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.keyresolver.impl.ServerNodeRateLimiterKeyResolver;
import com.zhangxiaofanfan.cloud.framework.ratelimiter.core.keyresolver.impl.UserRateLimiterKeyResolver;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@AutoConfiguration(after = ZxffRedisAutoConfiguration.class)
public class ZxffRateLimiterConfiguration {

    @Bean
    public RateLimiterAspect rateLimiterAspect(List<RateLimiterKeyResolver> keyResolvers, RateLimiterRedisDAO rateLimiterRedisDAO) {
        return new RateLimiterAspect(keyResolvers, rateLimiterRedisDAO);
    }

    @Bean
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public RateLimiterRedisDAO rateLimiterRedisDAO(RedissonClient redissonClient) {
        return new RateLimiterRedisDAO(redissonClient);
    }

    // ========== 各种 RateLimiterRedisDAO Bean ==========

    @Bean
    public DefaultRateLimiterKeyResolver defaultRateLimiterKeyResolver() {
        return new DefaultRateLimiterKeyResolver();
    }

    @Bean
    public UserRateLimiterKeyResolver userRateLimiterKeyResolver() {
        return new UserRateLimiterKeyResolver();
    }

    @Bean
    public ClientIpRateLimiterKeyResolver clientIpRateLimiterKeyResolver() {
        return new ClientIpRateLimiterKeyResolver();
    }

    @Bean
    public ServerNodeRateLimiterKeyResolver serverNodeRateLimiterKeyResolver() {
        return new ServerNodeRateLimiterKeyResolver();
    }

    @Bean
    public ExpressionRateLimiterKeyResolver expressionRateLimiterKeyResolver() {
        return new ExpressionRateLimiterKeyResolver();
    }

}
