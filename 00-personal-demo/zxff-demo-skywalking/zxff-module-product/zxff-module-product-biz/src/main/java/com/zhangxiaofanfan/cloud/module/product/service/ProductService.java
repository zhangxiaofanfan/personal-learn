package com.zhangxiaofanfan.cloud.module.product.service;

import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;

public interface ProductService {

    ProductDTO getProductById(Long id);
}
