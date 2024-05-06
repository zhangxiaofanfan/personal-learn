package com.zhangxiaofanfan.order.clients;

import com.zhangxiaofanfan.cloud.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用 feign 声明的访问 user 模块使用的客户端工具
 *
 * @author zhangxiaofanfan
 * @date 2024-01-23 10:53:12
 */
@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findUserById(@PathVariable("id") Long id);
}
