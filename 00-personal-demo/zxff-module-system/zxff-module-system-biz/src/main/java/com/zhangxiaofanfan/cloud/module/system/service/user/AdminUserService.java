package com.zhangxiaofanfan.cloud.module.system.service.user;

import com.zhangxiaofanfan.cloud.module.system.dao.pojo.user.AdminUserDO;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-16 18:57:44
 */
public interface AdminUserService {
    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getUserByUsername(String username);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    AdminUserDO getUser(Long id);
}
