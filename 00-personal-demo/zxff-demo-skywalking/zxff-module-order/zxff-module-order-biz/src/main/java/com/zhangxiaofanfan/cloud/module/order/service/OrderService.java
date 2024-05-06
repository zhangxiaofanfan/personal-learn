package com.zhangxiaofanfan.cloud.module.order.service;

import com.zhangxiaofanfan.cloud.module.order.dto.OrderDTO;

public interface OrderService {
    OrderDTO detailById(Long id);
}
