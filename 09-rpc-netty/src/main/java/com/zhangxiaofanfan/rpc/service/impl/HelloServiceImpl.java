package com.zhangxiaofanfan.rpc.service.impl;

import com.zhangxiaofanfan.rpc.service.HelloService;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-02 上午9:46
 * @description RPC 提供功能实现
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return String.format("hello, %s...", name);
    }
}
