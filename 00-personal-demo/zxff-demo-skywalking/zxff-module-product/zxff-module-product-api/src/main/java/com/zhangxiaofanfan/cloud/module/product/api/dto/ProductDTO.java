package com.zhangxiaofanfan.cloud.module.product.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:23:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "查询到的用户信息")
public class ProductDTO {
    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "MacBook Pro")
    private String productName;
    @Schema(description = "产品价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "8888.88")
    private Double productPrice;
    @Schema(description = "产品描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "笔记本电脑")
    private String productDesc;
}
