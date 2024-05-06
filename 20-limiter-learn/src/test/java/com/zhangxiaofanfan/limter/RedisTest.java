package com.zhangxiaofanfan.limter;

import com.zhangxiaofanfan.LimiterApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis 链接测试类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-12 10:33:32
 */
@Slf4j
@SpringBootTest(classes = LimiterApplication.class)
public class RedisTest {
    @Autowired
    private RedisTemplate<Object, Object> redisClient;

    @Test
    public void redisRedTest() {
        String testKey = "names";
        log.info("redis is {}", redisClient);
        System.out.println(redisClient.opsForValue().get(testKey));
    }
}
