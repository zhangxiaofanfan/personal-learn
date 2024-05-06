package com.zhangxiaofanfan.book.demo.serializer.impl;

import com.alibaba.fastjson2.JSON;
import com.zhangxiaofanfan.book.demo.serializer.Serializer;
import com.zhangxiaofanfan.book.demo.serializer.SerializerAlgorithm;

/**
 * 使用 fastjson 进行序列化和反序列化的实现
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 19:32:20
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializeAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
