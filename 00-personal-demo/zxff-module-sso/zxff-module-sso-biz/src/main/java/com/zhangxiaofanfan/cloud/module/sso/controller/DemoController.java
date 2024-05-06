package com.zhangxiaofanfan.cloud.module.sso.controller;

import com.zhangxiaofanfan.cloud.module.sso.dao.mysql.user.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-24 12:18:22
 */
@RestController
@RequestMapping("/sso-api/system/demo")
public class DemoController {

    @Autowired
    private SystemUserMapper systemUserMapper;

    @GetMapping("user")
    public String user() {
        return systemUserMapper.selectById(1).toString();
    }
}
