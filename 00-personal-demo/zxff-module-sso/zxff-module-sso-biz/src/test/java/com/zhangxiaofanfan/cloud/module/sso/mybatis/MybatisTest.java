package com.zhangxiaofanfan.cloud.module.sso.mybatis;

import com.zhangxiaofanfan.cloud.module.sso.dao.mysql.user.SystemUserMapper;
import com.zhangxiaofanfan.cloud.module.sso.dao.pojo.user.SystemUserDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-24 11:04:00
 */
@Slf4j
@SpringBootTest
public class MybatisTest {
    @Autowired
    private SystemUserMapper memberUserMapper;

    @Test
    public void userReadTest() {
        SystemUserDO user = memberUserMapper.selectById(1);
        log.info("user is {}", user);
    }
}
