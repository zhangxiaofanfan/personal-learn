package com.zhangxiaofanfan.cloud.module.product;

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
    public static final String NAME = "zxff-skywalking-product-service";

    public static final String PREFIX = RpcConstants.RPC_API_PREFIX + "/product";

    public static final String TAG_NAME = "RPC - 商品模块";
}