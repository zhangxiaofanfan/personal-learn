package com.zhangxiaofanfan.cloud.module.sso.service.user.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.zhangxiaofanfan.cloud.module.sso.dao.mysql.user.SystemUserMapper;
import com.zhangxiaofanfan.cloud.module.sso.dao.pojo.user.SystemUserDO;
import com.zhangxiaofanfan.cloud.module.sso.service.user.SystemUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-24 10:09:18
 */
@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Resource
    private SystemUserMapper memberUserMapper;

    @Override
    public SystemUserDO getUserByMobile(String mobile) {
        return memberUserMapper.selectByMobile(mobile);
    }

    @Override
    public List<SystemUserDO> getUserListByNickname(String nickname) {
        return memberUserMapper.selectListByNicknameLike(nickname);
    }

    @Override
    public SystemUserDO getUser(Long id) {
        return memberUserMapper.selectById(id);
    }

    @Override
    public List<SystemUserDO> getUserList(Collection<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return ListUtil.empty();
        }
        return memberUserMapper.selectBatchIds(ids);
    }
}
