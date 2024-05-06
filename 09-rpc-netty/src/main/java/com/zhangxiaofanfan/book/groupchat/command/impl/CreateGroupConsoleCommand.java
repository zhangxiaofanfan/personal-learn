package com.zhangxiaofanfan.book.groupchat.command.impl;

import com.zhangxiaofanfan.book.groupchat.command.ConsoleCommand;
import com.zhangxiaofanfan.book.groupchat.packet.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 发送创建群聊实现类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 09:01:26
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket request = new CreateGroupRequestPacket();
        System.out.print("【拉群群聊】输入userId列表, 多人之间使用英文逗号进行分割: ");

        String userIds = scanner.nextLine();
        request.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));

        channel.writeAndFlush(request);
    }
}
