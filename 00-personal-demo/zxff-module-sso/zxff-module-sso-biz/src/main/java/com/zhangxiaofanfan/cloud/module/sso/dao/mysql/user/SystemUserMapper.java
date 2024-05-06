package com.zhangxiaofanfan.cloud.module.sso.dao.mysql.user;

import com.zhangxiaofanfan.cloud.framework.mybatis.core.mapper.BaseMapperX;
import com.zhangxiaofanfan.cloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zhangxiaofanfan.cloud.module.sso.dao.pojo.user.SystemUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员 User Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SystemUserMapper extends BaseMapperX<SystemUserDO> {

    default SystemUserDO selectByMobile(String mobile) {
        return selectOne(SystemUserDO::getMobile, mobile);
    }

    default List<SystemUserDO> selectListByNicknameLike(String nickname) {
        return selectList(new LambdaQueryWrapperX<SystemUserDO>()
                .likeIfPresent(SystemUserDO::getNickname, nickname));
    }
}
