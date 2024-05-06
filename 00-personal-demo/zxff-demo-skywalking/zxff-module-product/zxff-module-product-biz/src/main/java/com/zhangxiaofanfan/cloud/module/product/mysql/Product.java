package com.zhangxiaofanfan.cloud.module.product.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:37:09
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "MacBook Pro")
    private String productName;
    @Schema(description = "产品价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "8888.88")
    private Double productPrice;
    @Schema(description = "产品描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "笔记本电脑")
    private String productDesc;
}
