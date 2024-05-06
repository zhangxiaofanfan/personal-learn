package com.zhangxiaofanfan.learn.myselfdemo.serializer.impl;

import com.alibaba.fastjson2.JSON;
import com.zhangxiaofanfan.learn.myselfdemo.serializer.Serializer;

/**
 * 使用 JSON 对数据进行序列化操作
 *
 * @author zhangxiaofanfan
 * @date 2024-02-19 16:22:54
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return JSON_SERIALIZER;
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
