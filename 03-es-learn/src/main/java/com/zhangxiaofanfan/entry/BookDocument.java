package com.zhangxiaofanfan.entry;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

/**
 * 访问 es 表使用的实体类
 *
 * @date 2023-07-12 10:23:19
 * @author zhangxiaofanfan
 */
@Data
@ToString
@Document(indexName = "book")
public class BookDocument {
    @Field("name")
    private String name;
    @Field("description")
    private String description;
    @Field("studymodel")
    private String studymodel;
    @Field("price")
    private Double price;
    @Field("timestamp")
    private String timestamp;
    @Field("pic")
    private String pic;
    @Field("tags")
    private List<String> tags;
}
