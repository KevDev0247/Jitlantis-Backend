package com.jitlantis.backend.API.dao;

import com.jitlantis.backend.API.dto.MainMenuDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMenuDao {

    List<MainMenuDto> getMainMenus(@Param("userId") Integer userId);
}
