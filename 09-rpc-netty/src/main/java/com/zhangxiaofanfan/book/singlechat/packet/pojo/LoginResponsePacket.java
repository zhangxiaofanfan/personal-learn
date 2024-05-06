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
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 22:42:04
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponsePacket extends Packet {
    private boolean success;
    private String reason;
    private String userId;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
