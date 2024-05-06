package com.zhangxiaofanfan;

import com.zhangxiaofanfan.user.UserApplication;
import com.zhangxiaofanfan.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * mybatis 测试类
 *
 * @author zhangxiaofanfan
 * @date 2024-01-18 09:50:25
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserApplication.class)
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void mybatisConnectionTest() {
        log.info("测试结果: {}", userMapper.selectById(1));
    }
}
