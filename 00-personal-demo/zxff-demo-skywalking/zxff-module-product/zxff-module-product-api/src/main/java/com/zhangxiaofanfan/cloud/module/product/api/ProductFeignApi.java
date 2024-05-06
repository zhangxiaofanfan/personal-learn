package com.zhangxiaofanfan.cloud.module.product.api;

import com.zhangxiaofanfan.cloud.module.product.ApiConstants;
import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 用户服务 feign 声明
 *
 * @date 2024-04-28 04:16:04
 * @author zhangxiaofanfan
 */
@Tag(name = ApiConstants.TAG_NAME)
@FeignClient(name = ApiConstants.NAME)
public interface ProductFeignApi {
    String PREFIX = ApiConstants.PREFIX;

    @GetMapping(PREFIX + "/{id}")
    @Operation(summary = "通过产品 id 获取产品信息")
    ProductDTO getProductById(@Valid @PathVariable("id") Long id);
}
