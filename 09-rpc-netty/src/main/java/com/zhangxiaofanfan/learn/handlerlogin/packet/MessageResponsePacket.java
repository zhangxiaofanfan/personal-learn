package com.zhangxiaofanfan.learn.handlerlogin.packet;

import com.zhangxiaofanfan.learn.handlerlogin.protocol.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 响应消息对象
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 10:09:12
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponsePacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
