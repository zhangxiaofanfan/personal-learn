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
 * 加入群聊请求数据包
 *
 * @author zhangxiaofanfan
 * @date 2024-03-20 18:44:57
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JoinGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;
    private String userId;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
