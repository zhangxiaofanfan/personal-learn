package com.zhangxiaofanfan.book.groupchat.command;

/**
 * 定义指令类型接口
 *
 * @author zhangxiaofanfan
 * @date 2024-03-15 09:47:29
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;

    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;

    Byte JOIN_GROUP_REQUEST = 7;
    Byte JOIN_GROUP_RESPONSE = 8;

    Byte QUIT_GROUP_REQUEST = 9;
    Byte QUIT_GROUP_RESPONSE = 10;

    Byte QUERY_GROUP_REQUEST = 11;
    Byte QUERY_GROUP_RESPONSE = 12;

    Byte SEND_GROUP_MESSAGE_REQUEST = 13;
    Byte SEND_GROUP_MESSAGE_RESPONSE = 14;

    Byte SEND_USER_MESSAGE_REQUEST = 15;
    Byte SEND_USER_MESSAGE_RESPONSE = 16;
}
