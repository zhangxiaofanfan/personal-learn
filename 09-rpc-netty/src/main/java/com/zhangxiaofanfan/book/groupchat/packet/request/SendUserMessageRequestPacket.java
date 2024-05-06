package com.zhangxiaofanfan.book.groupchat.packet.request;

import com.zhangxiaofanfan.book.groupchat.command.Command;
import com.zhangxiaofanfan.book.groupchat.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建群聊请求体
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 09:10:50
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendUserMessageRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.SEND_USER_MESSAGE_REQUEST;
    }
}
