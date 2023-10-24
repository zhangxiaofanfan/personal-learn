package com.zhangxiaofanfan.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户类
 *
 * @author zhangxiaofanfan
 * @date 2023-10-18 15:14:33
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 用户名
    private String user;
    // 用户年龄
    private Integer age;
}
