package com.zhangxiaofanfan;

import com.zhangxiaofanfan.adapter.HandlerAdapter;
import com.zhangxiaofanfan.config.AnnotationConfig;
import com.zhangxiaofanfan.server.handler.HandlerMapping;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-28 下午11:04
 * @description Servlet 规范实现服务调用
 */
public class Servlet extends HttpServlet implements ApplicationContextAware {
    ApplicationContext appContext;
    static Collection<HandlerMapping> handlerMappings;
    static Collection<HandlerAdapter> handlerAdapter;

    // 接收所有 request 请求
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandlerMapping(req);
        // 采用适配器模式
        HandlerAdapter adapter = getHandlerAdapter(handler);
        Object result = null;
        try {
            result = adapter.handler(req, resp, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.getWriter().println(result);
    }

    // get 方法
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Object handler = getHandlerMapping(req);
        // // 采用适配器模式
        // HandlerAdapter adapter = getHandlerAdapter(handler);
        // Object result = null;
        // try {
        //     result = adapter.handler(req, resp, handler);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // resp.getWriter().println(result);
    }

    // post 方法
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // 使用注解驱动的方式: 初始化 Spring 容器
        ApplicationContext context = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Map<String, HandlerMapping> handlerMappingMap = context.getBeansOfType(HandlerMapping.class);
        handlerMappings = handlerMappingMap.values();
        handlerAdapter = context.getBeansOfType(HandlerAdapter.class).values();
    }

    private Object getHandlerMapping(HttpServletRequest req) {
        if (handlerMappings != null) {
            for (HandlerMapping mapping : handlerMappings) {
                return mapping.getHandler(req.getRequestURI());
            }
        }
        return null;
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        if (handlerAdapter != null) {
            for (HandlerAdapter adapter : handlerAdapter) {
                if (adapter.support(handler)){
                    return adapter;
                }
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }
}
