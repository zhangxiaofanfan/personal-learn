package com.zhangxiaofanfan.server.handler;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午3:08
 * @description 定义获取控制器接口
 */
public interface HandlerMapping extends BeanPostProcessor {

    Object getHandler(String url);
}
