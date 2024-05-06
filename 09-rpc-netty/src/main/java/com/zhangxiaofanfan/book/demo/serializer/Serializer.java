package com.zhangxiaofanfan.book.demo.serializer;

import com.zhangxiaofanfan.book.demo.serializer.impl.JSONSerializer;

import static com.zhangxiaofanfan.book.demo.serializer.SerializerAlgorithm.JDK;
import static com.zhangxiaofanfan.book.demo.serializer.SerializerAlgorithm.JSON;


/**
 * 序列化实现规范
 *
 * @author zhangxiaofanfan
 * @date 2024-03-18 19:21:55
 */
public interface Serializer {

    // 默认使用 json 实现
    Serializer DEFAULT = new JSONSerializer();
    Serializer JDK_INSTANCE = new JSONSerializer();

    /**
     * 获取序列化算法标识
     *
     * @return 返回对应的序列化算法序号
     */
    byte getSerializeAlgorithm();

    /**
     * 序列化过程, 将java对象转换成字节数据过程
     *
     * @param object 需要被转换的 java 对象
     * @return java 对象序列化之后的字节数组
     */
    byte[] serialize(Object object);

    /**
     * 反序列化过程, 将字节数组转换成 java 对象的过程
     *
     * @param clazz 待反序列化的 java 类对象
     * @param bytes 待反序列化的 字节数组
     * @return 反序列化后的 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
