package com.zhangxiaofanfan.cloud.module.system.dao.mysql.oauth2;

import com.zhangxiaofanfan.cloud.framework.common.pojo.PageResult;
import com.zhangxiaofanfan.cloud.framework.mybatis.core.mapper.BaseMapperX;
import com.zhangxiaofanfan.cloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zhangxiaofanfan.cloud.module.system.controller.admin.oauth2.vo.token.OAuth2AccessTokenPageReqVO;
import com.zhangxiaofanfan.cloud.module.system.dao.pojo.oauth2.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-16 14:59:52
 */
@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapperX<OAuth2AccessTokenDO> {

    default OAuth2AccessTokenDO selectByAccessToken(String accessToken) {
        return selectOne(OAuth2AccessTokenDO::getAccessToken, accessToken);
    }

    default List<OAuth2AccessTokenDO> selectListByRefreshToken(String refreshToken) {
        return selectList(OAuth2AccessTokenDO::getRefreshToken, refreshToken);
    }

    default PageResult<OAuth2AccessTokenDO> selectPage(OAuth2AccessTokenPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2AccessTokenDO>()
                .eqIfPresent(OAuth2AccessTokenDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OAuth2AccessTokenDO::getUserType, reqVO.getUserType())
                .likeIfPresent(OAuth2AccessTokenDO::getClientId, reqVO.getClientId())
                .gt(OAuth2AccessTokenDO::getExpiresTime, LocalDateTime.now())
                .orderByDesc(OAuth2AccessTokenDO::getId));
    }
}
