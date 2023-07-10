package com.zhangxiaofanfan.pojo;

/**
 * 简单类, 容器注册使用
 * 
 * @date 2023-07-06 20:06:35
 * @author zhangxiaofanfan
 */
public class Hello {
    private String name;

    public String hello() {
        return "hello world by " + name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
