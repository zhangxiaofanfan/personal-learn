package com.zhangxiaofanfan.cloud.module.user.controller;

import com.zhangxiaofanfan.cloud.module.user.api.dto.UserDTO;
import com.zhangxiaofanfan.cloud.module.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:40:01
 */
@Tag(name = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "根据用户 id 查询用户详情")
    @GetMapping("/query")
    public UserDTO getUser(@RequestParam("id") Long id) {
        return userService.getUserById(id);
    }
}
