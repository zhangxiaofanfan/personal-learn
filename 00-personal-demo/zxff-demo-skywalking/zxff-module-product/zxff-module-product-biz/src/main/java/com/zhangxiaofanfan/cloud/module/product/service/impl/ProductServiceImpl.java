package com.zhangxiaofanfan.cloud.module.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;
import com.zhangxiaofanfan.cloud.module.product.mapper.ProductMapper;
import com.zhangxiaofanfan.cloud.module.product.mysql.Product;
import com.zhangxiaofanfan.cloud.module.product.service.ProductService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:41:11
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RedisTemplate<String, String> stringRedisClient;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO getProductById(Long id) {
        String productRedisKey = String.format("%s:%s", "sw:products", id);
        // 先从 redis 中获取
        String productJson = stringRedisClient.opsForValue().get(productRedisKey);
        if (!Strings.isBlank(productJson)) {
            return JSON.parseObject(productJson, ProductDTO.class);
        }
        // Redis 中不存在, 需要从 mysql 中获取, 并设置到 redis 中
        Product product = productMapper.selectById(id);
        ProductDTO result = ProductDTO.builder().build();
        result.setProductName(product.getProductName());
        result.setProductPrice(product.getProductPrice());
        result.setProductDesc(product.getProductDesc());
        stringRedisClient.opsForValue().set(productRedisKey, JSON.toJSONString(result));
        return result;
    }
}
