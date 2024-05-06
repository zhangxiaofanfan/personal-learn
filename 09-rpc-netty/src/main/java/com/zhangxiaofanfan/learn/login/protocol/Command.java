package com.zhangxiaofanfan.learn.login.protocol;

/**
 * TODO
 *
 * @date 2024-02-05 09:53:24
 * @author zhangxiaofanfan
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
