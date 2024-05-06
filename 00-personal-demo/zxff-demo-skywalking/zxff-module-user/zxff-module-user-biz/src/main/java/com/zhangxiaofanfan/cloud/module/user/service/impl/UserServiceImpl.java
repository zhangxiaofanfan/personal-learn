package com.zhangxiaofanfan.cloud.module.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxiaofanfan.cloud.module.user.api.dto.UserDTO;
import com.zhangxiaofanfan.cloud.module.user.dao.mysql.User;
import com.zhangxiaofanfan.cloud.module.user.mapper.UserMapper;
import com.zhangxiaofanfan.cloud.module.user.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:41:11
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate<String, String> stringRedisClient;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long id) {
        String userRedisKey = String.format("%s:%s", "sw:users", id);
        // 先从 redis 中获取
        String userJson = stringRedisClient.opsForValue().get(userRedisKey);
        if (!Strings.isBlank(userJson)) {
            return JSON.parseObject(userJson, UserDTO.class);
        }
        // Redis 中不存在, 需要从 mysql 中获取, 并设置到 redis 中
        User user = userMapper.selectById(id);
        UserDTO result = UserDTO.builder().build();
        result.setUserName(user.getUserName());
        result.setUserSex(user.getUserSex());
        result.setUserAge(user.getUserAge());
        stringRedisClient.opsForValue().set(userRedisKey, JSON.toJSONString(result));
        return result;
    }
}
