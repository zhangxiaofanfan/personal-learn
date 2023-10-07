package com.zhangxiaofanfan;

import java.util.Map;

/**
 * 订单业务类
 *
 * @date 2023-07-19 08:05:35
 * @author zhangxiaofanfan
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @return  返回新建的订单对象
     */
    Order create();

    /**
     * 支付订单
     *
     * @param id    订单编号
     * @return      返回支付后的订单
     */
    Order pay(int id);

    /**
     * 发货订单
     *
     * @param id    订单编号
     * @return      返回发货后的订单
     */
    Order deliver(int id);

    /**
     * 收货订单
     *
     * @param id    订单编号
     * @return      返回收货后的订单
     */
    Order receive(int id);

    /**
     * 获取所有订单信息
     *
     * @return  返回所有订单信息
     */
    Map<Integer, Order> getOrders();
}
