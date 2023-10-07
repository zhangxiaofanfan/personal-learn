package com.zhangxiaofanfan.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午4:26
 * @description TODO
 */
public interface HandlerAdapter {

    /**
     * 该对象是否支持此适配器
     */
    Boolean support(Object handler);


    Object handler(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception;
}
