package com.zhangxiaofanfan.cloud.module.system.dao.mysql.oauth2;

import com.zhangxiaofanfan.cloud.framework.common.pojo.PageResult;
import com.zhangxiaofanfan.cloud.framework.mybatis.core.mapper.BaseMapperX;
import com.zhangxiaofanfan.cloud.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zhangxiaofanfan.cloud.module.system.controller.admin.oauth2.vo.client.OAuth2ClientPageReqVO;
import com.zhangxiaofanfan.cloud.module.system.dao.pojo.oauth2.OAuth2ClientDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-16 18:09:04
 */
@Mapper
public interface OAuth2ClientMapper extends BaseMapperX<OAuth2ClientDO> {

    default PageResult<OAuth2ClientDO> selectPage(OAuth2ClientPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OAuth2ClientDO>()
                .likeIfPresent(OAuth2ClientDO::getName, reqVO.getName())
                .eqIfPresent(OAuth2ClientDO::getStatus, reqVO.getStatus())
                .orderByDesc(OAuth2ClientDO::getId));
    }

    default OAuth2ClientDO selectByClientId(String clientId) {
        return selectOne(OAuth2ClientDO::getClientId, clientId);
    }

}
