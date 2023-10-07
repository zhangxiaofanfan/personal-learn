package com.zhangxiaofanfan.server.handler.impl;

import com.zhangxiaofanfan.server.handler.HandlerMapping;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午3:08
 * @description TODO
 */
@Component
public class BeanIdHandlerMapping implements HandlerMapping {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public Object getHandler(String url) {
        return map.get(url);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("--------------------------");
        this.map.put(beanName, bean);
        return null;
    }
}
