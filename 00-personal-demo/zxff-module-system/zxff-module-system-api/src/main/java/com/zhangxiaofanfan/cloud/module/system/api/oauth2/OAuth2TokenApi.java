package com.zhangxiaofanfan.cloud.module.system.api.oauth2;

import com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult;
import com.zhangxiaofanfan.cloud.module.system.enums.ApiConstants;
import com.zhangxiaofanfan.cloud.module.system.api.oauth2.dto.OAuth2AccessTokenCheckRespDTO;
import com.zhangxiaofanfan.cloud.module.system.api.oauth2.dto.OAuth2AccessTokenCreateReqDTO;
import com.zhangxiaofanfan.cloud.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 负责 oAuth2 认证相关接口定义
 *
 * @author zhangxiaofanfan
 * @date 2024-04-14 21:24:06
 */
@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - OAuth2.0 令牌")
public interface OAuth2TokenApi {
    String PREFIX = ApiConstants.PREFIX + "/oauth2/token";

    /**
     * 校验 Token 的 URL 地址，主要是提供给 Gateway 使用
     */
    @SuppressWarnings("HttpUrlsUsage")
    String URL_CHECK = "http://" + ApiConstants.NAME + PREFIX + "/check";

    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建访问令牌")
    CommonResult<OAuth2AccessTokenRespDTO> createAccessToken(@Valid @RequestBody OAuth2AccessTokenCreateReqDTO reqDTO);

    @GetMapping(PREFIX + "/check")
    @Operation(summary = "校验访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    CommonResult<OAuth2AccessTokenCheckRespDTO> checkAccessToken(@RequestParam("accessToken") String accessToken);

    @DeleteMapping(PREFIX + "/remove")
    @Operation(summary = "移除访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    CommonResult<OAuth2AccessTokenRespDTO> removeAccessToken(@RequestParam("accessToken") String accessToken);

    @PutMapping(PREFIX + "/refresh")
    @Operation(summary = "刷新访问令牌")
    @Parameters({
            @Parameter(name = "refreshToken", description = "刷新令牌", required = true, example = "haha"),
            @Parameter(name = "clientId", description = "客户端编号", required = true, example = "zxff-source-code")
    })
    CommonResult<OAuth2AccessTokenRespDTO> refreshAccessToken(@RequestParam("refreshToken") String refreshToken,
                                                              @RequestParam("clientId") String clientId);


}
