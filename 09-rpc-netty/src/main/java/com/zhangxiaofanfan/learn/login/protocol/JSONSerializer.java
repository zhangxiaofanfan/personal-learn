package com.zhangxiaofanfan.learn.login.protocol;

import com.alibaba.fastjson2.JSON;

/**
 * JSON 序列化算法
 *
 * @author zhangxiaofanfan
 * @date 2024-02-05 10:06:04
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
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
