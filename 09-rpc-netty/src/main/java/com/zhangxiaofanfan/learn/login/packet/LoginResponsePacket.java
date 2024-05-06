package com.zhangxiaofanfan.learn.login.packet;

import com.zhangxiaofanfan.learn.login.protocol.Command;
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
 * @date 2024-02-05 09:54:35
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponsePacket extends Packet{
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
