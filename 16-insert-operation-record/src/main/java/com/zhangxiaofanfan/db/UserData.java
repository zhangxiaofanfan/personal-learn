package com.zhangxiaofanfan.db;

import com.zhangxiaofanfan.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 模拟系统数据库中的数据
 *
 * @author zhangxiaofanfan
 * @date 2023-10-24 16:40:24
 */
public class UserData {
    /**
     * 使用 map 方式模拟系统用户记录
     */
    public static final Map<Integer, User> users = new HashMap<>();
    public static final Map<Integer, Integer> moneys = new HashMap<>();
    static {
        users.put(1, new User("张小帆帆", 20));
        users.put(2, new User("杨小棉棉", 18));
        moneys.put(1, 10);
        moneys.put(2, 18);
    }
}
