package com.zhangxiaofanfan.test;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zhangxiaofanfan.TestInterface;

@SpringBootTest
public class MainTest {

    @Autowired
    private Map<String, TestInterface> map;

    @Test
    public void test01() {
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key).getClass().getName());
        }
    }
}
