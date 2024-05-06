package com.zhangxiaofanfan.oauth2.controller;

import com.zhangxiaofanfan.oauth2.entry.SysUser;
import com.zhangxiaofanfan.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-02 11:35:26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<SysUser> getUserList() {
        return userService.list();
    }
}
