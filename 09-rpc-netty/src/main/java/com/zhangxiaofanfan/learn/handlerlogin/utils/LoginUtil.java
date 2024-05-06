package com.zhangxiaofanfan.learn.handlerlogin.utils;

import com.zhangxiaofanfan.learn.handlerlogin.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 和登录相关的工具类
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 10:12:41
 */
public class LoginUtil {
    /**
     * 向已经完成登录的channel中设置登录表示符
     *
     * @param channel 连接对象
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断连接对象是否已经完成过登录
     *
     * @param channel 连接对象
     * @return 登录标识验证结果
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
