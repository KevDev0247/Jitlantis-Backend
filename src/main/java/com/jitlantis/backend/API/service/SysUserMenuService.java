package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.MainMenuDto;
import com.jitlantis.backend.API.model.SysUserMenu;

import java.util.List;

public interface SysUserMenuService extends IService<SysUserMenu> {

    List<MainMenuDto> getMainMenus(Integer userId, Integer isShow);
}
