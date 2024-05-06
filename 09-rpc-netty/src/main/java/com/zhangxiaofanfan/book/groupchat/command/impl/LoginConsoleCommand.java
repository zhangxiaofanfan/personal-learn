package com.zhangxiaofanfan.book.groupchat.command.impl;

import com.zhangxiaofanfan.book.groupchat.command.ConsoleCommand;
import com.zhangxiaofanfan.book.groupchat.packet.request.LoginRequestPacket;
import io.netty.channel.Channel;
import org.apache.logging.log4j.util.Strings;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * 登录命令
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 09:17:07
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        // 没有完成登录, 进行登录设置
        String loginString = getUserConsoleLoginString(scanner);
        String[] loginInfo = loginString.split("-");
        LoginRequestPacket login = LoginRequestPacket
                .builder()
                .username(loginInfo[0])
                .password(loginInfo[1])
                .build();
        channel.writeAndFlush(login);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("休眠过程中被打断, 只输出异常, 异常信息为: " + e.getMessage());
        }
    }

    private static String getUserConsoleLoginString(Scanner scanner) {
        System.out.println("请先完成登录, 登录格式:【用户名-密码】, 登录后按回车结束");
        while (true) {
            System.out.print("> ");
            String loginString = scanner.nextLine();
            if (Strings.isNotBlank(loginString) && loginString.split("-").length == 2) {
                return loginString;
            }
            System.out.println("登录信息失败, 请重新登录");
        }
    }
}
