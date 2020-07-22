package com.phiotonia.kniotcloud.backend.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.phiotonia.kniotcloud.backend.model.SysUser;
import org.mapstruct.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
}
