package com.zhangxiaofanfan.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2023-10-10 18:47:54
 */
@Slf4j
@Service
public class HelloService {
    public void methodA() {
        log.info("执行了 MethodA 方法..., 时间为: {}", new Date());
    }

    public void methodB() {
        log.info("执行了 MethodB 方法..., 时间为: {}", new Date());
    }
}
