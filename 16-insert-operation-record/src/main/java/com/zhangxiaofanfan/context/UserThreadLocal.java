package com.zhangxiaofanfan.context;

import com.zhangxiaofanfan.pojo.User;

/**
 * 用来保存一次请求过程中的用户信息
 *
 * @author zhangxiaofanfan
 * @date 2023-10-24 16:32:40
 */
public class UserThreadLocal {
    private static final ThreadLocal<User> USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(User user) {
        USER_THREAD_LOCAL.set(user);
    }

    public static void clear() {
        USER_THREAD_LOCAL.remove();
    }

    public static User get() {
        return USER_THREAD_LOCAL.get();
    }
}
