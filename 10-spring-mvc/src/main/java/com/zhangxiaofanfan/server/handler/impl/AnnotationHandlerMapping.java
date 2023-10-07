package com.zhangxiaofanfan.server.handler.impl;

import com.zhangxiaofanfan.RequestMappingInfo;
import com.zhangxiaofanfan.annotation.RequestMapping;
import com.zhangxiaofanfan.server.handler.HandlerMapping;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午3:17
 * @description TODO
 */
@Component
public class AnnotationHandlerMapping implements HandlerMapping {
    private ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();

    @Override
    public Object getHandler(String url) {
        return map.get(url);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(RequestMapping.class)) {
                String url = method.getAnnotation(RequestMapping.class).value();
                this.map.put(url, new RequestMappingInfo(url, method, bean));
            }
        }
        return null;
    }
}

