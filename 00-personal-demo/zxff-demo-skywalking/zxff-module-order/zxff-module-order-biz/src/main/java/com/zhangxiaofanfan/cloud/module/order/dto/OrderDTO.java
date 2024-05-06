package com.zhangxiaofanfan.cloud.module.order.dto;

import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;
import com.zhangxiaofanfan.cloud.module.user.api.dto.UserDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 15:47:52
 */
@Data
@Builder
@Schema(description = "查询到的用户信息")
public class OrderDTO {
    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long orderId;
    // 产品信息
    private ProductDTO product;
    // 用户信息
    private UserDTO user;
}
