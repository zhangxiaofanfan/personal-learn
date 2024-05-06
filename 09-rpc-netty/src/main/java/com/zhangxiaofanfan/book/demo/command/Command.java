package com.zhangxiaofanfan.book.demo.command;

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


}
