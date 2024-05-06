package com.zhangxiaofanfan.oauth2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangxiaofanfan.oauth2.entry.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author zhangxiaofanfan
 * @date 2024-03-02 11:25:48
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}
