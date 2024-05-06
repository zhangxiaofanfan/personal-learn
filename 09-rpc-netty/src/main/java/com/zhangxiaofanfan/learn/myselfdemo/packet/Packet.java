package com.zhangxiaofanfan.learn.myselfdemo.packet;

import lombok.Data;

/**
 * 数据包抽象父类
 *
 * @author zhangxiaofanfan
 * @date 2024-02-05 09:50:21
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令获取方法
     *
     * @return 待获取的指令
     */
    public abstract Byte getCommand();
}
