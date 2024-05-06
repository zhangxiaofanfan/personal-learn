package com.zhangxiaofanfan.cloud.module.system.dao.mysql.oauth2;

import com.zhangxiaofanfan.cloud.framework.mybatis.core.mapper.BaseMapperX;
import com.zhangxiaofanfan.cloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zhangxiaofanfan.cloud.module.system.dao.pojo.oauth2.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-16 16:39:37
 */
@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapperX<OAuth2RefreshTokenDO> {

    default int deleteByRefreshToken(String refreshToken) {
        return delete(new LambdaQueryWrapperX<OAuth2RefreshTokenDO>()
                .eq(OAuth2RefreshTokenDO::getRefreshToken, refreshToken));
    }

    default OAuth2RefreshTokenDO selectByRefreshToken(String refreshToken) {
        return selectOne(OAuth2RefreshTokenDO::getRefreshToken, refreshToken);
    }

}
