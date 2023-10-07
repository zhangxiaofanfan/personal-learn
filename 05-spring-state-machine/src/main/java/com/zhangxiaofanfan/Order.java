package com.zhangxiaofanfan;

/**
 * 订单实体类, 省略了其他信息, 只保留了id和状态位
 *
 * @author zhangxiaofanfan
 * @date 2023-07-19 12:47:05
 */
public class Order {
    
    private int id;

    private OrderStatus status;

    @Override
    public String toString() {
        return "订单号: " + id + ", 订单状态: " + status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
