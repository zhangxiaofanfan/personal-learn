package com.zhangxiaofanfan.book.groupchat.serializer.impl;

import com.zhangxiaofanfan.book.groupchat.serializer.Serializer;
import com.zhangxiaofanfan.book.groupchat.serializer.SerializerAlgorithm;

/**
 * 使用 jdk 进行序列化和反序列化的实现
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 19:32:20
 */
public class JDKSerializer implements Serializer {
    @Override
    public byte getSerializeAlgorithm() {
        return SerializerAlgorithm.JDK;
    }

    @Override
    public byte[] serialize(Object object) {
        return null;
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return null;
    }
}
