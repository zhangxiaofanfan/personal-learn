package com.zhangxiaofanfan.cloud.module.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-29 14:03:50
 */
@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> stringRedisClient;

    @Test
    public void redisRead() {
        String redisKey = "name";
        String name = stringRedisClient.opsForValue().get(redisKey);
        log.info("name is {}", name);
    }
}
