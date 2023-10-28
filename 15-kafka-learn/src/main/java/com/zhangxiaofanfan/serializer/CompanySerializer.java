package com.zhangxiaofanfan.serializer;

import com.zhangxiaofanfan.pojo.Company;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Company 类序列化器
 *
 * @author zhangxiaofanfan
 * @date 2023-10-28 19:47:30
 */
public class CompanySerializer implements Serializer<Company> {

    /**
     * 类对象在传输之前使用该方法对 对象 进行序列化操作
     *
     * @param topic 主题关联哪些数据
     * @param data  需要进行序列化的对象
     * @return  对象序列化之后的字节数组对象
     */
    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }
        byte[] name, address;
        if (data.getName() != null) {
            name = data.getName().getBytes(StandardCharsets.UTF_8);
        } else {
            name = new byte[0];
        }
        if (data.getAddress() != null) {
            address = data.getAddress().getBytes(StandardCharsets.UTF_8);
        } else {
            address = new byte[0];
        }
        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
        buffer.putInt(name.length);
        buffer.put(name);
        buffer.putInt(address.length);
        buffer.put(address);
        return buffer.array();
    }
}
