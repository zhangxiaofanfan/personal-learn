package com.zhangxiaofanfan.cloud.module.sso.service.user;

import com.zhangxiaofanfan.cloud.module.sso.dao.pojo.user.SystemUserDO;

import java.util.Collection;
import java.util.List;

/**
 * 会员用户 Service 接口
 *
 * @author 芋道源码
 */
public interface SystemUserService {

    /**
     * 通过手机查询用户
     *
     * @param mobile 手机
     * @return 用户对象
     */
    SystemUserDO getUserByMobile(String mobile);

    /**
     * 基于用户昵称，模糊匹配用户列表
     *
     * @param nickname 用户昵称，模糊匹配
     * @return 用户信息的列表
     */
    List<SystemUserDO> getUserListByNickname(String nickname);

    /**
     * 通过用户 ID 查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    SystemUserDO getUser(Long id);

    /**
     * 通过用户 ID 查询用户们
     *
     * @param ids 用户 ID
     * @return 用户对象信息数组
     */
    List<SystemUserDO> getUserList(Collection<Long> ids);
}
