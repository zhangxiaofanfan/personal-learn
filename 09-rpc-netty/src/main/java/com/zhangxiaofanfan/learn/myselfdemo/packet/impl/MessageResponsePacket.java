package com.zhangxiaofanfan.learn.myselfdemo.packet.impl;

import com.zhangxiaofanfan.learn.myselfdemo.command.Commands;
import com.zhangxiaofanfan.learn.myselfdemo.packet.Packet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-02-20 16:22:42
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponsePacket extends Packet {

    private String username;

    private String message;

    @Override
    public Byte getCommand() {
        return Commands.MESSAGE_REQUEST_PACKET.getId();
    }
}
