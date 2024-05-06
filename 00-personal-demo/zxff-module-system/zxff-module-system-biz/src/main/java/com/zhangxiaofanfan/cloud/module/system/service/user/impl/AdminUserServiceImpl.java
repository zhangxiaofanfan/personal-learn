package com.zhangxiaofanfan.cloud.module.system.service.user.impl;

import com.zhangxiaofanfan.cloud.module.system.dao.mysql.user.AdminUserMapper;
import com.zhangxiaofanfan.cloud.module.system.dao.pojo.user.AdminUserDO;
import com.zhangxiaofanfan.cloud.module.system.service.user.AdminUserService;
import jakarta.annotation.Resource;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-16 19:43:42
 */
public class AdminUserServiceImpl implements AdminUserService {
    @Resource
    private AdminUserMapper userMapper;

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public AdminUserDO getUser(Long id) {
        return userMapper.selectById(id);
    }
}
