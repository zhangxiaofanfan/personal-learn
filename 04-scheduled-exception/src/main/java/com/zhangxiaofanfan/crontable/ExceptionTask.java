package com.zhangxiaofanfan.crontable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试 非预期异常 定时任务
 *
 * @author zhangxiaofanfan
 * @date 2023-07-18 15:32:21
 */

@Slf4j
@Component
public class ExceptionTask {

    @Scheduled(cron = "0 */1 * * * ?")
    public void cronMethod() {
        log.info("非预期异常之前");
        exceptionMethod();
        log.info("非预期异常之后");
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void exceptionMethod() {
        String s= "";
        Integer.parseInt(s);
    }
}
