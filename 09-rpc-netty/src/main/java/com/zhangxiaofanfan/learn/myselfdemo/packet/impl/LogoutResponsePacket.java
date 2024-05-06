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
 * 登录响应数据包实现
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 15:49:56
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LogoutResponsePacket extends Packet {
    private boolean success;
    private String session;

    @Override
    public Byte getCommand() {
        return Commands.LOGIN_RESPONSE_PACKET.getId();
    }
}
