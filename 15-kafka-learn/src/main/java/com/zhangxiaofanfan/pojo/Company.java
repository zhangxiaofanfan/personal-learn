package com.zhangxiaofanfan.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 学习自定义序列化器使用
 *
 * @author zhangxiaofanfan
 * @date 2023-10-28 19:44:29
 */
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private String name;
    private String address;
}
