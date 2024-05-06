package com.zhangxiaofanfan.cloud.module.product.api;

import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;
import com.zhangxiaofanfan.cloud.module.product.mapper.ProductMapper;
import com.zhangxiaofanfan.cloud.module.product.mysql.Product;
import com.zhangxiaofanfan.cloud.module.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:28:38
 */
@Validated
@RestController
public class ProductFeignServiceImpl implements ProductFeignApi {

    @Autowired
    private ProductMapper ProductMapper;
    @Autowired
    private ProductService productService;

    @Override
    public ProductDTO getProductById(Long id) {
        return productService.getProductById(id);
    }
}

