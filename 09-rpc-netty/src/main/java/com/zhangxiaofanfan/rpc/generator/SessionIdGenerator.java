package com.zhangxiaofanfan.rpc.generator;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-09-01 下午3:51
 * @description 会话id生成器
 */
public class SessionIdGenerator {
    private static AtomicInteger index = new AtomicInteger();

    /**
     * 获取下一个会话id
     * @return 会话id
     */
    public static Integer nextId() {
        return index.getAndIncrement();
    }
}
