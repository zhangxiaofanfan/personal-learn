package com.zhangxiaofanfan.cloud.module.infra.service.logger.impl;

import com.zhangxiaofanfan.cloud.module.infra.api.logger.dto.ApiAccessLogCreateReqDTO;
import com.zhangxiaofanfan.cloud.module.infra.service.logger.ApiAccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;

/**
 * API 访问日志 Service 实现类
 *
 * @author 芋道源码
 */
@Slf4j
@Service
@Validated
public class ApiAccessLogServiceImpl implements ApiAccessLogService {

    // @Resource
    // private ApiAccessLogMapper apiAccessLogMapper;

    @Override
    public void createApiAccessLog(ApiAccessLogCreateReqDTO createDTO) {
        log.info("ApiAccessLogServiceImpl createApiAccessLog 函数执行了, 入参: {}", createDTO);
        // ApiAccessLogDO apiAccessLog = BeanUtils.toBean(createDTO, ApiAccessLogDO.class);
        // apiAccessLogMapper.insert(apiAccessLog);
    }
    //
    // @Override
    // public PageResult<ApiAccessLogDO> getApiAccessLogPage(ApiAccessLogPageReqVO pageReqVO) {
    //     return apiAccessLogMapper.selectPage(pageReqVO);
    // }
    //
    // @Override
    // @SuppressWarnings("DuplicatedCode")
    // public Integer cleanAccessLog(Integer exceedDay, Integer deleteLimit) {
    //     int count = 0;
    //     LocalDateTime expireDate = LocalDateTime.now().minusDays(exceedDay);
    //     // 循环删除，直到没有满足条件的数据
    //     for (int i = 0; i < Short.MAX_VALUE; i++) {
    //         int deleteCount = apiAccessLogMapper.deleteByCreateTimeLt(expireDate, deleteLimit);
    //         count += deleteCount;
    //         // 达到删除预期条数，说明到底了
    //         if (deleteCount < deleteLimit) {
    //             break;
    //         }
    //     }
    //     return count;
    // }

}
