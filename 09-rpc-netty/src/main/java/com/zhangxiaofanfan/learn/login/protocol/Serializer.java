package com.zhangxiaofanfan.learn.login.protocol;

/**
 * 定义序列化标准
 *
 * @author zhangxiaofanfan
 * @date 2024-02-05 09:57:24
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *
     * @return 返回序列化算法标识
     */
    byte getSerializerAlgorithm();

    /**
     * 将java对象转换成二进制数据
     *
     * @param object 待转换的java对象
     * @return java对应的二进制数据
     */
    byte[] serialize(Object object);

    /**
     * 二进制数据转换成 java 对象
     *
     * @param clazz 泛型类对象
     * @param bytes 二进制数据
     * @return 二进制数据对应的 java 对象
     * @param <T> 待转换成为的泛型类型
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
