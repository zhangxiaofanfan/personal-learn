package com.zhangxiaofanfan.learn.myselfdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装用户相关数据使用
 *
 * @author zhangxiaofanfan
 * @date 2024-02-20 15:18:24
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private Integer sex;
    private Integer age;
}
