package com.zhangxiaofanfan;

/**
 * 订单状态转义事件类型枚举
 *
 * @date 2023-07-19 12:59:53
 * @author zhangxiaofanfan
 */
public enum OrderStatusChangeEvent {
    /** 支付 */
    PAYED,
    /** 发货 */
    DELIVER,
    /** 确认收货 */
    RECEIVED,
}
