package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.BaseMenuDto;
import com.jitlantis.backend.API.model.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> selectMenuListByRoleId(Integer roleId);

    List<BaseMenuDto> selectFirstMenuDtoList();
}
