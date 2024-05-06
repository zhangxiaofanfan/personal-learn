package com.zhangxiaofanfan.entry;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * 访问 es 表使用的实体类
 *
 * @date 2023-07-12 10:23:19
 * @author zhangxiaofanfan
 */
@Data
@Document(indexName = "test_index")
public class TestIndexDocument {

    @Field("test_field")
    private String testField;
}
