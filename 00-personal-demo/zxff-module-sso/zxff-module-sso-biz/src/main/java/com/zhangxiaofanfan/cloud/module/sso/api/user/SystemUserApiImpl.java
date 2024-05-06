package com.zhangxiaofanfan.cloud.module.sso.api.user;

import com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult;
import com.zhangxiaofanfan.cloud.module.sso.api.user.dto.SystemUserRespDTO;
import com.zhangxiaofanfan.cloud.module.sso.convert.user.SystemUserConvert;
import com.zhangxiaofanfan.cloud.module.sso.dao.pojo.user.SystemUserDO;
import com.zhangxiaofanfan.cloud.module.sso.service.user.SystemUserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult.success;

/**
 * 会员用户的 API 实现类
 *
 * @author 芋道源码
 */
@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class SystemUserApiImpl implements SystemUserApi {

    @Resource
    private SystemUserService userService;

    @Override
    public CommonResult<SystemUserRespDTO> getUser(Long id) {
        SystemUserDO user = userService.getUser(id);
        return success(SystemUserConvert.INSTANCE.convert2(user));
    }

    @Override
    public CommonResult<List<SystemUserRespDTO>> getUserList(Collection<Long> ids) {
        List<SystemUserDO> userList = userService.getUserList(ids);
        return success(SystemUserConvert.INSTANCE.convertList2(userList));
    }

    @Override
    public CommonResult<List<SystemUserRespDTO>> getUserListByNickname(String nickname) {
        List<SystemUserDO> userList = userService.getUserListByNickname(nickname);
        return success(SystemUserConvert.INSTANCE.convertList2(userList));
    }

    @Override
    public CommonResult<SystemUserRespDTO> getUserByMobile(String mobile) {
        SystemUserDO user = userService.getUserByMobile(mobile);
        return success(SystemUserConvert.INSTANCE.convert2(user));
    }

}
