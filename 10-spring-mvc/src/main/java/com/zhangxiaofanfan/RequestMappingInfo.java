package com.zhangxiaofanfan;

import java.lang.reflect.Method;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午4:32
 * @description TODO
 */
public class RequestMappingInfo {
    private String url;
    private Method method;
    private Object bean;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public RequestMappingInfo() {
    }

    public RequestMappingInfo(String url, Method method, Object bean) {
        this.url = url;
        this.method = method;
        this.bean = bean;
    }
}
