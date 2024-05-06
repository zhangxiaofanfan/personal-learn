package com.zhangxiaofanfan.cloud.module.order.config;

import com.zhangxiaofanfan.cloud.module.product.api.ProductFeignApi;
import com.zhangxiaofanfan.cloud.module.user.api.UserFeignApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:52:30
 */
@Configuration
@EnableFeignClients(clients = {
        UserFeignApi.class,
        ProductFeignApi.class
})
public class FeignConfig {
}
