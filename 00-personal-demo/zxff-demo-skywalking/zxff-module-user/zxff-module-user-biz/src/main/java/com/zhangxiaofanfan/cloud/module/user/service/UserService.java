package com.zhangxiaofanfan.cloud.module.user.service;

import com.zhangxiaofanfan.cloud.module.user.api.dto.UserDTO;

public interface UserService {

    UserDTO getUserById(Long id);
}
