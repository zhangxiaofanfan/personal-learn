package com.zhangxiaofanfan.cloud.module.sso.convert.user;

import com.zhangxiaofanfan.cloud.module.sso.api.user.dto.SystemUserRespDTO;
import com.zhangxiaofanfan.cloud.module.sso.dao.pojo.user.SystemUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


// @Mapper(uses = {AddressConvert.class})
@Mapper
public interface SystemUserConvert {

    SystemUserConvert INSTANCE = Mappers.getMapper(SystemUserConvert.class);

    SystemUserRespDTO convert2(SystemUserDO bean);

    List<SystemUserRespDTO> convertList2(List<SystemUserDO> list);
}
