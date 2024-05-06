package com.zhangxiaofanfan.learn.myselfdemo.util;

import com.zhangxiaofanfan.learn.myselfdemo.packet.impl.LoginRequestPacket;
import com.zhangxiaofanfan.learn.myselfdemo.pojo.User;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

/**
 * 和登录相关的工具类
 *
 * @author zhangxiaofanfan
 * @date 2024-02-20 15:16:44
 */
@Slf4j
public class LoginUtils {
    public static final AttributeKey<String> SESSION_KEY = AttributeKey.newInstance("session");
    private static final Map<String, String> SESSIONS = new HashMap<>();
    private static final Map<String, User> USERS;
    private static final Map<String, Channel> USER_CHANNEL = new HashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    // 模拟数据库数据
    static {
        USERS = new HashMap<>();
        USERS.put(
                "zhangxiaofanfan",
                User.builder().username("zhangxiaofanfan").password("zhangxiaofanfan").age(20).sex(1).build()
        );
        USERS.put(
                "yangxiaomianmian",
                User.builder().username("yangxiaomianmian").password("yangxiaomianmian").age(18).sex(0).build()
        );
    }


    /**
     * 登录操作
     *
     * @param username 登录账号
     * @param password 登录密码
     * @param channel 数据通路
     * @return 登录是否成功
     */
    public static String login(String username, String password, Channel channel) {
        if (Strings.isBlank(username) || Strings.isBlank(password)) {
            log.warn("账号或密码为空, 登录失败");
            return null;
        }
        if (!USERS.containsKey(username)) {
            log.warn("系统中不存在账户[{}], 确认后重新登录", username);
            return null;
        }
        if (!USERS.get(username).getPassword().equals(password)) {
            log.warn("用户[{}]登录密码错误, 重新登录", username);
            return null;
        }
        // 保存登录信息
        String sessionToken = UUID.randomUUID().toString().replace("-", "");
        SESSIONS.put(sessionToken, username);
        USER_CHANNEL.put(sessionToken, channel);
        log.debug("SESSIONS: {}", SESSIONS);
        log.debug("USER_CHANNEL: {}", USER_CHANNEL);
        return sessionToken;
    }

    /**
     * 退出登录操作
     *
     * @param username
     * @param sessionToken
     * @param channel
     * @return
     */
    public static String logout(String username, String sessionToken, Channel channel) {
        // 1. 删除会话 session
        SESSIONS.remove(sessionToken);
        USER_CHANNEL.remove(sessionToken);
        log.debug("SESSIONS: {}", SESSIONS);
        log.debug("USER_CHANNEL: {}", USER_CHANNEL);
        return sessionToken;
    }


    /**
     * 控制台登录操作
     *
     * @param channel 登录使用的channel对象
     */
    public static void consoleLogin(Channel channel) {
        System.out.println("请输入账号: ");
        String username = SCANNER.nextLine();
        System.out.println("请输出密码: ");
        String password = SCANNER.nextLine();
        LoginRequestPacket loginUser = new LoginRequestPacket();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        channel.writeAndFlush(loginUser);
    }


    /**
     * 判断连接是否完成过登录
     *
     * @param channel 连接通路
     * @return 是否完成过登录
     */
    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(SESSION_KEY);
    }

    public static void main(String[] args) {
        System.out.println();
    }
}