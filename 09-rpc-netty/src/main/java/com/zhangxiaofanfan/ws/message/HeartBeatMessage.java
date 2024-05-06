package com.zhangxiaofanfan.ws.message;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 心跳机制
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:39:54
 */
@Data
public class HeartBeatMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1290124171105321491L;


    /**
     * 发送心跳消息的用户Id
     */
    private String userId;

}
