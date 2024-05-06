package com.zhangxiaofanfan.book.demo.attribute;

import io.netty.util.AttributeKey;

/**
 * 为 channel 中设置登录标识符
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 08:19:44
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
