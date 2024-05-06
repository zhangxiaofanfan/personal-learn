package com.zhangxiaofanfan.cloud.module.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhangxiaofanfan.cloud.module.order.dao.mysql.Order;
import com.zhangxiaofanfan.cloud.module.order.dto.OrderDTO;
import com.zhangxiaofanfan.cloud.module.order.mapper.OrderMapper;
import com.zhangxiaofanfan.cloud.module.order.service.OrderService;
import com.zhangxiaofanfan.cloud.module.product.api.ProductFeignApi;
import com.zhangxiaofanfan.cloud.module.product.api.dto.ProductDTO;
import com.zhangxiaofanfan.cloud.module.user.api.UserFeignApi;
import com.zhangxiaofanfan.cloud.module.user.api.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 12:38:29
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserFeignApi userFeignService;
    @Autowired
    private ProductFeignApi productFeignService;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDTO detailById(Long id) {
        Order order = orderMapper.selectById(id);
        SendResult<String, Object> sendResult;
        if (order != null) {
            UserDTO user = userFeignService.getUserById(order.getUserId());
            ProductDTO product = productFeignService.getProductById(order.getProductId());
            log.info("user is {}", user);
            log.info("product is {}", product);
            // // 将订单添加到消息队列中
            OrderDTO orderResult = OrderDTO.builder().orderId(order.getId()).user(user).product(product).build();
            try {
                sendResult = kafkaTemplate.send("SW-ORDER-SERVICE", JSON.toJSONString(orderResult)).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
            log.info("[MQ Result] 发送结果: {}", sendResult.getRecordMetadata());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return orderResult;
        }
        return null;
    }
}
