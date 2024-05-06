package com.zhangxiaofanfan.learn.myselfdemo.command;

import lombok.Getter;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 16:54:05
 */
@Getter
public enum Commands {

    LOGIN_REQUEST_PACKET((byte) 0),
    LOGIN_RESPONSE_PACKET((byte) 1),
    MESSAGE_REQUEST_PACKET((byte) 2),
    MESSAGE_RESPONSE_PACKET((byte) 3),
    LOGOUT_REQUEST_PACKET((byte) 4),
    LOGOUT_RESPONSE_PACKET((byte) 5),
    ;

    private final byte id;

    Commands(byte id) {
        this.id = id;
    }

    public static Commands getCommandByByte(byte command) {
        for (Commands commandObj : Commands.values()) {
            if (commandObj.getId() == command) {
                return commandObj;
            }
        }
        return null;
    }
}
