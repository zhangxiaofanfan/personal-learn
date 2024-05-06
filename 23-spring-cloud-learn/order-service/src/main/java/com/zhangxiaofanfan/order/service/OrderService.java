package com.zhangxiaofanfan.order.service;

import com.zhangxiaofanfan.cloud.entity.order.Order;
import com.zhangxiaofanfan.cloud.entity.user.User;
import com.zhangxiaofanfan.order.clients.UserClient;
import com.zhangxiaofanfan.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 查询订单内容
        Order order = orderMapper.selectById(orderId);
        // 根据订单中的用户编号使用 http 请求获取下游用户信息
        User user = userClient.findUserById(order.getUserId());
        // 并将用户信息注入到订单对象中
        order.setUser(user);
        return order;
    }
}
