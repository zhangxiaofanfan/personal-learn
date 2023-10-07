package com.zhangxiaofanfan.spring.exception;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 上午10:19
 * @description TODO
 */
public class NoSuchBeanException extends RuntimeException {

    public NoSuchBeanException(String message) {
        super(message);
    }
}
