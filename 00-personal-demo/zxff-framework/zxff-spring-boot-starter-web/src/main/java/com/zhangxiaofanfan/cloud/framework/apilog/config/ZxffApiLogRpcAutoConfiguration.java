package com.zhangxiaofanfan.cloud.framework.apilog.config;

import com.zhangxiaofanfan.cloud.module.infra.api.logger.ApiAccessLogApi;
import com.zhangxiaofanfan.cloud.module.infra.api.logger.ApiErrorLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * API 日志使用到 Feign 的配置项
 *
 * @author 芋道源码
 */
@AutoConfiguration
@EnableFeignClients(clients = {ApiAccessLogApi.class, ApiErrorLogApi.class})
public class ZxffApiLogRpcAutoConfiguration {
}
