package com.zhangxiaofanfan.entry;

import org.springframework.data.elasticsearch.annotations.Field;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-09 16:46:28
 */
public class BaseTableEntity<T> {
    @Field(name = "_index")
    private String index;
    private String _type;
    private String _id;
    private String _version;
    private String _seq_no;
    private String _primary_term;
    private boolean found;
    private T _source;
}
