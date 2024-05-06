package com.zhangxiaofanfan.book.demo.packet.pojo;

import com.zhangxiaofanfan.book.demo.command.Command;
import com.zhangxiaofanfan.book.demo.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 消息请求数据包
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
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
