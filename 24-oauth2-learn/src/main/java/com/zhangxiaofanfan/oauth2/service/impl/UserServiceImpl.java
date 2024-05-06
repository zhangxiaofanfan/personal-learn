package com.zhangxiaofanfan.oauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangxiaofanfan.oauth2.entry.SysUser;
import com.zhangxiaofanfan.oauth2.mapper.UserMapper;
import com.zhangxiaofanfan.oauth2.service.UserService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-02 11:34:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
}
