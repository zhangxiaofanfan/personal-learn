package com.zhangxiaofanfan.cloud.module.system.dao.redis.oauth2;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.zhangxiaofanfan.cloud.framework.common.util.collection.CollectionUtils;
import com.zhangxiaofanfan.cloud.framework.common.util.json.JsonUtils;
import com.zhangxiaofanfan.cloud.module.system.dao.pojo.oauth2.OAuth2AccessTokenDO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.zhangxiaofanfan.cloud.module.system.dao.redis.RedisKeyConstants.OAUTH2_ACCESS_TOKEN;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-16 16:45:41
 */
@Repository
public class OAuth2AccessTokenRedisDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public OAuth2AccessTokenDO get(String accessToken) {
        String redisKey = formatKey(accessToken);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), OAuth2AccessTokenDO.class);
    }

    public void set(OAuth2AccessTokenDO accessTokenDO) {
        String redisKey = formatKey(accessTokenDO.getAccessToken());
        // 清理多余字段，避免缓存
        accessTokenDO.setUpdater(null);
        accessTokenDO.setUpdateTime(null);
        accessTokenDO.setCreateTime(null);
        accessTokenDO.setCreator(null);
        accessTokenDO.setDeleted(null);
        long time = LocalDateTimeUtil.between(LocalDateTime.now(), accessTokenDO.getExpiresTime(), ChronoUnit.SECONDS);
        if (time > 0) {
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(accessTokenDO), time, TimeUnit.SECONDS);
        }
    }

    public void delete(String accessToken) {
        String redisKey = formatKey(accessToken);
        stringRedisTemplate.delete(redisKey);
    }

    public void deleteList(Collection<String> accessTokens) {
        List<String> redisKeys = CollectionUtils.convertList(accessTokens, OAuth2AccessTokenRedisDAO::formatKey);
        stringRedisTemplate.delete(redisKeys);
    }

    private static String formatKey(String accessToken) {
        return String.format(OAUTH2_ACCESS_TOKEN, accessToken);
    }

}
