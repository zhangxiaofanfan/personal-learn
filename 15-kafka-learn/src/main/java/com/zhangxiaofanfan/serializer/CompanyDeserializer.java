package com.zhangxiaofanfan.serializer;

import com.zhangxiaofanfan.pojo.Company;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * Company 类反序列化器
 *
 * @author zhangxiaofanfan
 * @date 2023-10-28 22:00:35
 */
public class CompanyDeserializer implements Deserializer<Company> {
    @Override
    public Company deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new SerializationException("Size of data received by DemoDeserializer is shorter than exception");
        }
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int nameLength, addressLength;
        String name, address;
        nameLength = buffer.getInt();
        byte[] nameBytes = new byte[nameLength];
        buffer.get(nameBytes);
        addressLength = buffer.getInt();
        byte[] addressBytes = new byte[addressLength];
        buffer.get(addressBytes);
        name = new String(nameBytes, StandardCharsets.UTF_8);
        address = new String(addressBytes, StandardCharsets.UTF_8);
        return Company.builder().name(name).address(address).build();
    }
}
