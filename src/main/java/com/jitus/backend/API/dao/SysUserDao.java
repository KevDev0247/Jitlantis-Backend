package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.SysUser;
import org.mapstruct.Mapper;

@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
}
