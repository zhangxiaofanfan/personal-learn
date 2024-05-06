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
 * 登录请求数据包实现
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 15:46:27
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet {

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Commands.LOGIN_REQUEST_PACKET.getId();
    }
}
