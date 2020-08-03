package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.SysUser;
import org.mapstruct.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
}
