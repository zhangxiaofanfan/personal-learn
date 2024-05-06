package com.zhangxiaofanfan.oauth;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhangxiaofanfan.oauth2.entry.SysUser;
import com.zhangxiaofanfan.oauth2.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-02 11:25:22
 */
@Slf4j
@SpringBootTest
// @RunWith(SpringRunner.class)
public class MybatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<>();
        List<SysUser> sysUsers = userMapper.selectList(query);
        log.info("user is {}", sysUsers);
    }
}
