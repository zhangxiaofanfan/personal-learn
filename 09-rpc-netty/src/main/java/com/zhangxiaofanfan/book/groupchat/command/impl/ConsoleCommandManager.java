package com.zhangxiaofanfan.book.groupchat.command.impl;

import com.zhangxiaofanfan.book.groupchat.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 控制台命令管理器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 09:00:05
 */
public class ConsoleCommandManager implements ConsoleCommand {
    private final Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
        consoleCommandMap.put("queryGroup", new QueryGroupConsoleCommand());
        consoleCommandMap.put("sendGroupMsg", new SendGroupMessageConsoleCommand());
        // consoleCommandMap.put("sendUserMsg", new SendGroupMessageConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.printf("请输出需要执行的操作, 系统支持操作【%s】\n", String.join(",", consoleCommandMap.keySet()));
        System.out.print("> ");
        String command = scanner.nextLine();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand == null) {
            System.out.printf("无法识别【%s】指令, 请重新输入!\n", command);
        } else {
            consoleCommand.exec(scanner, channel);
        }
    }
}
