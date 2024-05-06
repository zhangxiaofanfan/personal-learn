package com.zhangxiaofanfan.ws.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:42:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 3147392908880170895L;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

}
