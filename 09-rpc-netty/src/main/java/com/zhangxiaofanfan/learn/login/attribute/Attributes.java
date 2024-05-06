package com.zhangxiaofanfan.learn.login.attribute;

import io.netty.util.AttributeKey;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 10:14:04
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
