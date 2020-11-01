package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.dto.MainMenuDto;
import com.jitlantis.backend.API.model.SysUserMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysUserMenuDao extends BaseMapper<SysUserMenu> {

    List<MainMenuDto> getMainMenus(@Param("userId") Integer userId, @Param("isShow") Integer isShow);
}
