package com.zhangxiaofanfan.learn.handlerlogin.packet;

import com.zhangxiaofanfan.learn.handlerlogin.protocol.Command;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录请求数据包
 *
 * @author zhangxiaofanfan
 * @date 2024-02-05 09:54:35
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestPacket extends Packet {
    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
