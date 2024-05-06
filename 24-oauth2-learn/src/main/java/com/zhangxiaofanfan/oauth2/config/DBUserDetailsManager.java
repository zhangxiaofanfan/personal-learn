package com.zhangxiaofanfan.oauth2.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangxiaofanfan.oauth2.entry.SysUser;
import com.zhangxiaofanfan.oauth2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 基于数据库管理用户登录
 *
 * @author zhangxiaofanfan
 * @date 2024-03-04 08:50:57
 */
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        query.eq(SysUser::getUsername, username);
        SysUser user = userMapper.selectOne(query);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    true,
                    true,
                    true,
                    true,
                    new ArrayList<>()
            );
        }
    }
}
