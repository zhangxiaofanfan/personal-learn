package com.zhangxiaofanfan.book.singlechat.packet;

import lombok.Data;

/**
 * 定义netty数据传输对象的顶级抽象父类
 *
 * @author zhangxiaofanfan
 * @date 2024-03-15 09:48:19
 */
@Data
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 获取指令的抽象方法
     *
     * @return 返回数据包对应的指令类型
     */
    public abstract Byte getCommand();
}
