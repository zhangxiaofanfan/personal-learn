package com.zhangxiaofanfan;

import com.zhangxiaofanfan.entry.BookDocument;
import com.zhangxiaofanfan.entry.TestIndexDocument;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

/**
 * 主测试类
 *
 * @author zhangxiaofanfan
 * @date 2023-07-11 19:57:46
 */
@Slf4j
@SpringBootTest
public class MainTest {
    @Autowired
    private ElasticsearchTemplate esClient;

    @Test
    public void test01() {
        TestIndexDocument t = esClient.get("3", TestIndexDocument.class);
        System.out.println("结果是: " + (t != null ? t.getTestField() : null));
    }

    @Test
    public void bookReadTest01() {
        BookDocument book = esClient.get("1", BookDocument.class);
        log.info("book is {}", book);
    }
}
