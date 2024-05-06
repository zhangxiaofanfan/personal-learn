package com.zhangxiaofanfan.cloud.module.order;

import com.zhangxiaofanfan.cloud.module.order.dao.mysql.Order;
import com.zhangxiaofanfan.cloud.module.order.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 15:15:35
 */
@Slf4j
@SpringBootTest
public class MybatisPlusTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void orderRead() {
        Order order = orderMapper.selectById(1L);
        log.info("user is {}", order);
    }
}
