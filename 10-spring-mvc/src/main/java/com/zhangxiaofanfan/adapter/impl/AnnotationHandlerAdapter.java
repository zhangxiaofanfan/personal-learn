package com.zhangxiaofanfan.adapter.impl;

import com.zhangxiaofanfan.RequestMappingInfo;
import com.zhangxiaofanfan.adapter.HandlerAdapter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午4:31
 * @description TODO
 */
@Component
public class AnnotationHandlerAdapter implements HandlerAdapter {
    @Override
    public Boolean support(Object handler) {
        return handler instanceof RequestMappingInfo;
    }

    @Override
    public Object handler(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Map<String, String[]> parameterMap = req.getParameterMap(); // 请求携带的参数
        RequestMappingInfo requestMappingInfo = (RequestMappingInfo) handler;
        Method method = requestMappingInfo.getMethod();
        Parameter[] parameters = method.getParameters();    // 默认是没有参数的

        return method.invoke(requestMappingInfo.getBean(), new Object[parameters.length]);
    }
}
