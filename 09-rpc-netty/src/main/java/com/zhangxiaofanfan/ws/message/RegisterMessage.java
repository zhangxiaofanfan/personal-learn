package com.zhangxiaofanfan.ws.message;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:40:44
 */
@Data
public class RegisterMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -4953615574208683170L;
    /**
     * 注册到服务端的用户Id
     */
    private String userId;
}
