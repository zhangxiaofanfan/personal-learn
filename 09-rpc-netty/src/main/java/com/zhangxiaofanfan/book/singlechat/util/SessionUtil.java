package com.zhangxiaofanfan.book.singlechat.util;

import com.zhangxiaofanfan.book.singlechat.attribute.Attributes;
import com.zhangxiaofanfan.book.singlechat.session.Session;
import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装和会话相关的工具类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 22:06:26
 */
public class SessionUtil {
    private static final String USER_1 = "zhangxiaofanfan";
    private static final String USER_2 = "yangxiaomianmian";
    private static final ConcurrentHashMap<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (!hasLogin(channel)) {
            return ;
        }
        userIdChannelMap.remove(getSession(channel).getUserId());
        channel.attr(Attributes.SESSION).set(null);
    }

    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.SESSION).set(null);
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.getOrDefault(userId, null);
    }


    public static boolean login(String username, String password) {
        return  USER_1.equals(username) && USER_1.equals(password) ||
                USER_2.equals(username) && USER_2.equals(password);
    }
}
