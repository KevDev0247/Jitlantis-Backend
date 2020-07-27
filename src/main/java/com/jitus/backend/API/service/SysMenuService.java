package com.jitus.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitus.backend.API.dto.BaseMenuDto;
import com.jitus.backend.API.model.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> selectMenuListByRoleId(Integer roleId);

    List<BaseMenuDto> selectFirstMenuDtoList();
}
