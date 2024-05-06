package com.zhangxiaofanfan.book.demo.util;

import com.zhangxiaofanfan.book.demo.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关工具类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 08:20:49
 */
public class LoginUtil {

    private static final Map<String, User> USERS = new HashMap<>();

    static {
        USERS.put("zhangxiaofanfan", User.builder().username("zhangxiaofanfan").password("yangxiaomianmian").build());
    }
    /**
     * 为 channel 通道设置登录标识符号
     *
     * @param channel 已完成登录的连接通路
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    /**
     * 判断连接通路是否完成过登录
     *
     * @param channel 连接通路
     * @return 登录标识, true表示完成过登录, false表示未完成需要进行登录
     */
    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> login = channel.attr(Attributes.LOGIN);
        return login.get() != null;
    }

    /**
     * 完成用户登录操作
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 登录成功标志位
     */
    public static boolean login(String username, String password) {
        if (!USERS.containsKey(username)) {
            return false;
        }
        return USERS.get(username).getPassword().equals(password);
    }


    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private Integer userId;
        private String username;
        private String password;
    }
}
