package com.zhangxiaofanfan.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangxiaofanfan.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
/**
 * 操作表 tb_user 使用的 mapper 接口
 *
 * @date 2024-01-18 03:42:41
 * @author zhangxiaofanfan
 */
public interface UserMapper extends BaseMapper<User> {

}