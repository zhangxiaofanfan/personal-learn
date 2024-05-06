package com.zhangxiaofanfan.book.groupchat.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 控制台可以执行的操作抽象
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 08:56:12
 */
public interface ConsoleCommand {

    /**
     * 控制台可以执行的命令
     *
     * @param scanner 获取控制台输入的对象
     * @param channel 连接
     */
    void exec(Scanner scanner, Channel channel);
}
