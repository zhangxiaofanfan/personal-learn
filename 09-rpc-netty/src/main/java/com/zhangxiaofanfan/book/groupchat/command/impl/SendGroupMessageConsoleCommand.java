package com.zhangxiaofanfan.book.groupchat.command.impl;

import com.zhangxiaofanfan.book.groupchat.command.ConsoleCommand;
import com.zhangxiaofanfan.book.groupchat.packet.request.SendGroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * 发送群聊消息实现类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:43:11
 */
public class SendGroupMessageConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        SendGroupMessageRequestPacket request = new SendGroupMessageRequestPacket();

        System.out.print("输入需要发送消息的群聊id: ");
        request.setGroupId(scanner.nextLine());

        System.out.print("请输入需要发送的消息: ");
        request.setMessage(scanner.nextLine());
        channel.writeAndFlush(request);
    }
}
