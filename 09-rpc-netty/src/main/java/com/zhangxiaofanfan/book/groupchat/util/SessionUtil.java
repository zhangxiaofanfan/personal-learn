package com.zhangxiaofanfan.book.groupchat.util;

import com.zhangxiaofanfan.book.groupchat.attribute.Attributes;
import com.zhangxiaofanfan.book.groupchat.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 封装和会话相关的工具类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 22:06:26
 */
public class SessionUtil {
    private static final String USER_1 = "小帆";
    private static final String USER_2 = "小棉";
    private static final String USER_3 = "小莹";
    private static final ConcurrentHashMap<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, ChannelGroup> groupIdGroupMap = new ConcurrentHashMap<>();

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
                USER_2.equals(username) && USER_2.equals(password) ||
                USER_3.equals(username) && USER_3.equals(password)
                ;
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return groupIdGroupMap.getOrDefault(groupId, null);
    }

    public static void bindChannelGroup(String groupId, ChannelGroup group) {
        groupIdGroupMap.put(groupId, group);
    }
}
