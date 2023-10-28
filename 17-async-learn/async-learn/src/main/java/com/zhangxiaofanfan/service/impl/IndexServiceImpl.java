package com.zhangxiaofanfan.service.impl;

import com.zhangxiaofanfan.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2023-10-27 09:53:21
 */
@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    @Override
    @Async
    public Future<String> index1() {
        log.info("index1 thread name is {}", Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new AsyncResult<>("index1");
    }

    @Override
    public String index2() {
        return "index2";
    }
}
