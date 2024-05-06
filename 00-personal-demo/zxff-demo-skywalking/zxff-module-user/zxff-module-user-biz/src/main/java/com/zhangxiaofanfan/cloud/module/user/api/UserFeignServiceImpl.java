package com.zhangxiaofanfan.cloud.module.user.api;

import com.zhangxiaofanfan.cloud.module.user.api.dto.UserDTO;
import com.zhangxiaofanfan.cloud.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-04-28 16:28:38
 */
@Validated
@RestController
public class UserFeignServiceImpl implements UserFeignApi {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO getUserById(Long id) {
        return userService.getUserById(id);
    }
}

