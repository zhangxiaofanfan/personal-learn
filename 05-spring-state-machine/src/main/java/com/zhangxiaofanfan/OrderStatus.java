package com.zhangxiaofanfan;

/**
 * 订单状态枚举类
 *
 * @date 2023-07-19 12:57:28
 * @author zhangxiaofanfan
 */
public enum OrderStatus {
    /** 待支付 */
    WAIT_PAYMENT,
    /** 待发货 */
    WAIT_DELIVER,
    /** 待收货 */
    WAIT_RECEIVE,
    /** 订单完成 */
    FINISH,
    ;
}
