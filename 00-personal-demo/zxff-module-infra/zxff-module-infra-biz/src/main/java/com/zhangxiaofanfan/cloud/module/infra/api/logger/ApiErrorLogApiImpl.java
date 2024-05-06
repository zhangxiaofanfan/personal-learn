package com.zhangxiaofanfan.cloud.module.infra.api.logger;

import com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult;
import com.zhangxiaofanfan.cloud.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import com.zhangxiaofanfan.cloud.module.infra.service.logger.ApiErrorLogService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import static com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult.success;


@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class ApiErrorLogApiImpl implements ApiErrorLogApi {

    @Resource
    private ApiErrorLogService apiErrorLogService;

    @Override
    public CommonResult<Boolean> createApiErrorLog(ApiErrorLogCreateReqDTO createDTO) {
        apiErrorLogService.createApiErrorLog(createDTO);
        return success(true);
    }
}
