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
 * 登录请求类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 19:15:51
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
