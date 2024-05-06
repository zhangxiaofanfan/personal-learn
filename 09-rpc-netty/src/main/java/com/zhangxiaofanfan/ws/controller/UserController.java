package com.zhangxiaofanfan.ws.controller;

import com.zhangxiaofanfan.ws.config.NettyConfig;
import com.zhangxiaofanfan.ws.pojo.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author zhangxiaofanfan
 * @date 2024-03-22 10:47:24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 返回在线的UserId
     */
    @CrossOrigin(originPatterns = {
            "http://localhost:8081",
            "http://sso.server.com:9999",
            "http://10.40.129.179:8081",
            "http://localhost:5173"
    })
    @GetMapping("/online/list")
    public Map<String, Object> onlineList() {
        Map<String, Object> response = new HashMap<>();

        List<User> list = new ArrayList<>();
        NettyConfig.getOnlineUserChannelMap().forEach((key, value) -> {
            User user = new User(key, key);
            list.add(user);
        });
        response.put("code", 200);
        response.put("msg", "success");
        response.put("data", list);
        return response;
    }

}
