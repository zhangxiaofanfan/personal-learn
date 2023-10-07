package com.zhangxiaofanfan.spring.myinterface;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 下午2:38
 * @description 如果组件需要回调设置名称时实现此接口
 */
public interface BeanNameAware {
    void setName(String beanName);
}
