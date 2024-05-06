package com.zhangxiaofanfan.ws.enums;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:42:55
 */
public enum MessageTypeEnum {
    TEXT("普通文本消息"),
    HEARTBEAT("心跳数据"),
    REGISTER("注册数据");

    MessageTypeEnum(String desc) {
    }
}

