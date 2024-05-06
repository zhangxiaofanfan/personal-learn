package com.zhangxiaofanfan.book.groupchat.serializer;

/**
 * 定义序列化算法
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 19:28:48
 */
public interface SerializerAlgorithm {
    /**
     * json 序列化标识
     */
    byte JSON = 1;

    /**
     * jdk 原生序列化
     */
    byte JDK = 2;

}
