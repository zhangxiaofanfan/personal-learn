package com.zhangxiaofanfan.learn.handlerlogin.attribute;

import io.netty.util.AttributeKey;

/**
 * 用来做登录使用的属性
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 10:14:04
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
