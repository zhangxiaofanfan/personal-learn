package com.zhangxiaofanfan.cloud.module.product.controller;

import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;
import com.zhangxiaofanfan.cloud.module.product.mysql.Product;
import com.zhangxiaofanfan.cloud.module.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:40:01
 */
@Tag(name = "商品模块")
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "根据用户 id 查询商品详情")
    @GetMapping("/query")
    public ProductDTO getProduct(@RequestParam("id") Long id) {
        return productService.getProductById(id);
    }
}
