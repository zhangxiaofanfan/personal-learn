package com.zhangxiaofanfan.cloud.module.order.controller;

import com.zhangxiaofanfan.cloud.module.order.dto.OrderDTO;
import com.zhangxiaofanfan.cloud.module.order.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 12:35:30
 */
@Tag(name = "订单模块")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private  OrderService orderService;

    @GetMapping("/{id}")
    public OrderDTO detailById(@PathVariable("id") Long id) {
        return orderService.detailById(id);
    }
}
