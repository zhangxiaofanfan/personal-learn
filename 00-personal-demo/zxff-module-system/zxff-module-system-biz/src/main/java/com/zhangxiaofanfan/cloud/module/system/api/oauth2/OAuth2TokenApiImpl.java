package com.zhangxiaofanfan.cloud.module.system.api.oauth2;

import com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult;
import com.zhangxiaofanfan.cloud.framework.common.util.object.BeanUtils;
import com.zhangxiaofanfan.cloud.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.zhangxiaofanfan.cloud.module.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import com.zhangxiaofanfan.cloud.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import com.zhangxiaofanfan.cloud.module.system.dao.pojo.oauth2.OAuth2AccessTokenDO;
import com.zhangxiaofanfan.cloud.module.system.service.oauth2.OAuth2TokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import static com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult.success;

/**
 * RPC 调用接口实现类, 提供 RESTful API 接口，给 Feign 调用
 *
 * @author zhangxiaofanfan
 * @date 2024-04-15 09:43:41
 */
@RestController
@Validated
public class OAuth2TokenApiImpl implements OAuth2TokenApi {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    @Override
    @Operation(description = "创建访问令牌")
    public CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(OAuth2AccessTokenCreateReqDTO reqDTO) {
        return success(OAuth2AccessTokenRespDTO.builder().accessToken("测试访问token").build());
        // OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(
        //         reqDTO.getUserId(), reqDTO.getUserType(), reqDTO.getClientId(), reqDTO.getScopes());
        // return success(BeanUtils.toBean(accessTokenDO, OAuth2AccessTokenRespDTO.class));
    }

    @Override
    public CommonResult<OAuth2AccessTokenCheckRespDTO> checkAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.checkAccessToken(accessToken);
        return success(BeanUtils.toBean(accessTokenDO, OAuth2AccessTokenCheckRespDTO.class));
    }

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> removeAccessToken(String accessToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.removeAccessToken(accessToken);
        return success(BeanUtils.toBean(accessTokenDO, OAuth2AccessTokenRespDTO.class));
    }

    @Override
    public CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(String refreshToken, String clientId) {
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.refreshAccessToken(refreshToken, clientId);
        return success(BeanUtils.toBean(accessTokenDO, OAuth2AccessTokenRespDTO.class));
    }
}
