package com.zhangxiaofanfan.book.groupchat.command.impl;

import com.zhangxiaofanfan.book.groupchat.command.ConsoleCommand;
import com.zhangxiaofanfan.book.groupchat.packet.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送加入群聊实现类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:43:11
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket request = new JoinGroupRequestPacket();

        System.out.print("输入需要加入的群聊id: ");

        String groupId = scanner.nextLine();
        request.setGroupId(groupId);

        channel.writeAndFlush(request);
    }
}
