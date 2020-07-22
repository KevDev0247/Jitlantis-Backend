package com.phiotonia.kniotcloud.backend.service;

import com.baomidou.mybatisplus.service.IService;
import com.phiotonia.kniotcloud.backend.dto.BaseMenuDto;
import com.phiotonia.kniotcloud.backend.model.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> selectMenuListByRoleId(Integer roleId);

    List<BaseMenuDto> selectFirstMenuDtoList();
}
