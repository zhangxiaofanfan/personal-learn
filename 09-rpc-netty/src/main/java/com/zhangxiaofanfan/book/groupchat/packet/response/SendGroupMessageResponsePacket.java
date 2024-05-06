package com.zhangxiaofanfan.book.groupchat.packet.response;

import com.zhangxiaofanfan.book.groupchat.command.Command;
import com.zhangxiaofanfan.book.groupchat.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 创建群聊响应体
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
public class SendGroupMessageResponsePacket extends Packet {
    private boolean success;
    private String groupId;
    private String userId;
    private String reason;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.SEND_GROUP_MESSAGE_RESPONSE;
    }
}
