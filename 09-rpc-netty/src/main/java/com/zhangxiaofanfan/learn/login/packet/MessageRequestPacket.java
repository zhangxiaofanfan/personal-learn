package com.zhangxiaofanfan.learn.login.packet;

import com.zhangxiaofanfan.learn.login.protocol.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 请求消息对象
 *
 * @author zhangxiaofanfan
 * @date 2024-02-18 10:06:51
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
