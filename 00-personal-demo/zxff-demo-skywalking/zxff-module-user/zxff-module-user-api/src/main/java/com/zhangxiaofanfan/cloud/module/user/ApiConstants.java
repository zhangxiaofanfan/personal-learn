package com.zhangxiaofanfan.cloud.module.user;

import com.zhangxiaofanfan.cloud.module.common.enums.RpcConstants;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:12:47
 */
public class ApiConstants {
    /**
     * 服务名
     *
     * 注意，需要保证和 spring.application.name 保持一致
     */
    public static final String NAME = "zxff-skywalking-user-service";

    public static final String PREFIX = RpcConstants.RPC_API_PREFIX + "/user";

    public static final String TAG_NAME = "RPC - 用户模块";

    public static final String VERSION = "1.0.0";
}