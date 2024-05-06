package com.zhangxiaofanfan.user.service;

import com.zhangxiaofanfan.user.mapper.UserMapper;
import com.zhangxiaofanfan.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作用户使用的服务类对象
 *
 * @date 2024-01-18 03:43:29
 * @author zhangxiaofanfan
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        return userMapper.selectById(id);
    }
}