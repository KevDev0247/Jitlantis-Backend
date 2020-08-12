package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.dto.BaseMenuDto;
import com.jitlantis.backend.API.model.SysMenu;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {

    List<SysMenu> selectMenuListByRoleId(@Param("roleId") Integer roleId);

    List<BaseMenuDto> selectFirstMenuDtoList();
}
