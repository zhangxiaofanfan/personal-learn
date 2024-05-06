package com.zhangxiaofanfan.dubbo.provider;

import com.zhangxiaofanfan.duubo.commons.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * Dubbo demo service 实现类
 *
 * @author zhangxiaofanfan
 * @date 2023-12-09 10:03:00
 */
@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
