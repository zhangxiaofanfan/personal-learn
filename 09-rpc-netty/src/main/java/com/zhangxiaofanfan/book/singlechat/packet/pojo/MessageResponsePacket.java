package com.zhangxiaofanfan.book.singlechat.packet.pojo;

import com.zhangxiaofanfan.book.singlechat.command.Command;
import com.zhangxiaofanfan.book.singlechat.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 消息响应数据包
 *
 * @author zhangxiaofanfan
 * @date 2024-03-19 08:15:02
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
