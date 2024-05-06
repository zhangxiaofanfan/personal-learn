package com.zhangxiaofanfan.cloud.module.system.enums;

import com.zhangxiaofanfan.cloud.framework.common.enums.RpcConstants;

/**
 * zxff-module-system-api 项目接口定义
 *
 * @author zhangxiaofanfan
 * @date 2024-04-14 21:26:17
 */
public class ApiConstants {
    /**
     * 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String NAME = "system-server";

    public static final String PREFIX = RpcConstants.RPC_API_PREFIX +  "/system";

    public static final String VERSION = "1.0.0";
}
