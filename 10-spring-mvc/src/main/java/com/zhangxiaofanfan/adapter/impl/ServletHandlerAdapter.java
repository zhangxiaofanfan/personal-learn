package com.zhangxiaofanfan.adapter.impl;

import com.zhangxiaofanfan.Servlet;
import com.zhangxiaofanfan.adapter.HandlerAdapter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午4:29
 * @description 基于 Servlet 的 Handler 适配器实现
 */
@Component
public class ServletHandlerAdapter implements HandlerAdapter {
    @Override
    public Boolean support(Object handler) {
        return handler instanceof Servlet;
    }

    @Override
    public Object handler(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        ((Servlet)handler).service(req, resp);
        return null;
    }
}
