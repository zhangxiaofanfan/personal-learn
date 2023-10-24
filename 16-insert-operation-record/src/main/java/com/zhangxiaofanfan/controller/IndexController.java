package com.zhangxiaofanfan.controller;

import cn.hutool.json.JSONObject;
import com.zhangxiaofanfan.aop.OperationRecord;
import com.zhangxiaofanfan.context.UserThreadLocal;
import com.zhangxiaofanfan.db.UserData;
import com.zhangxiaofanfan.enums.OperationEntryEnum;
import com.zhangxiaofanfan.enums.OperationEnum;
import com.zhangxiaofanfan.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 测试使用的控制器类
 *
 * @author zhangxiaofanfan
 * @date 2023-10-18 15:09:18
 */
@Slf4j
@RestController
public class IndexController {

    /**
     * 新增用户记录
     *
     * @param user  新增的用户记录
     * @return      返回新增的用户记录
     */
    @PostMapping("/create")
    @OperationRecord(operationType = OperationEntryEnum.USER, operation = OperationEnum.CREATE)
    public User createRecord(@RequestBody User user) {
        log.info("create user is {}", user);
        UserData.users.put(UserData.users.size() + 1, user);
        return UserData.users.get(UserData.users.size());
    }

    /**
     * 删除用户记录
     *
     * @param userId    需要删除的用户标识 id
     * @return          返回删除的用户记录
     */
    @DeleteMapping("/delete/{user_id}")
    @OperationRecord(operationType = OperationEntryEnum.USER, operation = OperationEnum.DELETE)
    public User deleteRecord(@PathVariable("user_id") Integer userId) {
        log.info("delete user_id is {}", userId);
        User user = null;
        if (UserData.users.containsKey(userId)) {
            user = UserData.users.get(userId);
            UserData.users.remove(userId);
        }
        return user;
    }

    /**
     * 修改用户记录
     *
     * @param userId    需要修改的用户标识 id
     * @param user      修改的用户记录
     * @return          返回修改之前的记录
     */
    @PutMapping("/update/{user_id}")
    @OperationRecord(operationType = OperationEntryEnum.USER, operation = OperationEnum.UPDATE)
    public User updateRecord(@PathVariable("user_id") Integer userId, @RequestBody User user) {
        log.info("update user_id is {}, user is {}", userId, user);
        if (userId == null || userId < 0) {
            return null;
        }
        User originUser = UserData.users.getOrDefault(userId, null);
        UserData.users.put(userId, user);
        return originUser;
    }

    /**
     * 查找用户记录
     *
     * @param userId    需要查训的用户标识 id
     * @return          查找的用户记录
     */
    @GetMapping("/query/{user_id}")
    @OperationRecord(operationType = OperationEntryEnum.USER, operation = OperationEnum.QUERY)
    public User readRecord(@PathVariable("user_id") Integer userId) {
        log.info("query user_id is {}", userId);
        return UserData.users.getOrDefault(userId, null);
    }

    /**
     * 查询所有的用户信息
     *
     * @return  返回所有的用户记录
     */
    @GetMapping("/users")
    public Map<Integer, User> queryUser() {
        log.info("query User...");
        return UserData.users;
    }

    /**
     * 查询所有的用户信息
     *
     * @return  返回所有的用户记录
     */
    @GetMapping("/money/{user_id}")
    @OperationRecord(operationType = OperationEntryEnum.MONEY, operation = OperationEnum.QUERY)
    public JSONObject queryMoney(@PathVariable("user_id") Integer userId) {
        log.info("query money user_id is {}", userId);
        JSONObject json = new JSONObject();
        json.set("user_id", userId);
        json.set("money", UserData.moneys.getOrDefault(userId, 0));
        return json;
    }

    /**
     * 测试请求过程中的用户封装信息
     *
     * @return  返回封装的用户信息
     */
    @GetMapping("/filter/user")
    public User filterUser() {
        log.info("query filter user method running...");
        return UserThreadLocal.get();
    }
}
