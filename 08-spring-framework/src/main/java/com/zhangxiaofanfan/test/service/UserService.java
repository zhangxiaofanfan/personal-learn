package com.zhangxiaofanfan.test.service;

import com.zhangxiaofanfan.spring.annotation.Autowired;
import com.zhangxiaofanfan.spring.annotation.Component;

/**
 * @author 张晓帆帆
 * @version 1.0
 * @projectName 05-my-framework
 * @date 2021-08-29 上午11:33
 * @description TODO
 */
@Component
public class UserService {
    @Autowired
    public IndexService indexService;
}
