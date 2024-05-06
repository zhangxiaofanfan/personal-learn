package com.zhangxiaofanfan.book.groupchat.command.impl;

import com.zhangxiaofanfan.book.groupchat.command.ConsoleCommand;
import com.zhangxiaofanfan.book.groupchat.packet.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送加入群聊实现类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:43:11
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket request = new QuitGroupRequestPacket();

        System.out.print("输入需要退出的群聊id: ");

        String groupId = scanner.nextLine();
        request.setGroupId(groupId);

        channel.writeAndFlush(request);
    }
}
