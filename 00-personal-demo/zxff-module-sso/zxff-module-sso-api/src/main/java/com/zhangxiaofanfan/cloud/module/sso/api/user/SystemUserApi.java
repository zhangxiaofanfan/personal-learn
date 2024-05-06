package com.zhangxiaofanfan.cloud.module.sso.api.user;

import com.zhangxiaofanfan.cloud.framework.common.pojo.CommonResult;
import com.zhangxiaofanfan.cloud.module.sso.api.user.dto.SystemUserRespDTO;
import com.zhangxiaofanfan.cloud.module.sso.enums.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.zhangxiaofanfan.cloud.framework.common.util.collection.CollectionUtils.convertMap;

/**
 * 声明可以被用来调用的 RPC-API 接口
 *
 * @author zhangxiaofanfan
 * @date 2024-04-23 19:24:57
 */
public interface SystemUserApi {

    String PREFIX = ApiConstants.PREFIX + "/user";

    @GetMapping(PREFIX + "/get")
    @Operation(summary = "获得会员用户信息")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    CommonResult<SystemUserRespDTO> getUser(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/list")
    @Operation(summary = "获得会员用户信息们")
    @Parameter(name = "ids", description = "用户编号的数组", example = "1,2", required = true)
    CommonResult<List<SystemUserRespDTO>> getUserList(@RequestParam("ids") Collection<Long> ids);

    /**
     * 获得会员用户 Map
     *
     * @param ids 用户编号的数组
     * @return 会员用户 Map
     */
    default Map<Long, SystemUserRespDTO> getUserMap(Collection<Long> ids) {
        List<SystemUserRespDTO> list = getUserList(ids).getCheckedData();
        return convertMap(list, SystemUserRespDTO::getId);
    }

    @GetMapping(PREFIX + "/list-by-nickname")
    @Operation(summary = "基于用户昵称，模糊匹配用户列表")
    @Parameter(name = "nickname", description = "用户昵称，模糊匹配", required = true, example = "土豆")
    CommonResult<List<SystemUserRespDTO>> getUserListByNickname(@RequestParam("nickname") String nickname);

    @GetMapping(PREFIX + "/get-by-mobile")
    @Operation(summary = "基于手机号，精准匹配用户")
    @Parameter(name = "mobile", description = "基于手机号，精准匹配用户", required = true, example = "1560")
    CommonResult<SystemUserRespDTO> getUserByMobile(@RequestParam("mobile") String mobile);

}
