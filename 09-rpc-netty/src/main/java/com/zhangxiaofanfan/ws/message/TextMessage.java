package com.zhangxiaofanfan.ws.message;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:41:07
 */
@Data
public class TextMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -4851870722684661727L;

    /**
     * 发送消息的用户Id
     */
    private String userId;

    /**
     * 消息的接收者
     */
    private String receiver;

    /**
     * 用户发送的消息
     */
    private String msg;

}
