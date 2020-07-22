package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.SysMenuDao;
import com.phiotonia.kniotcloud.backend.dto.BaseMenuDto;
import com.phiotonia.kniotcloud.backend.model.SysMenu;
import com.phiotonia.kniotcloud.backend.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> selectMenuListByRoleId(Integer roleId) {
        return null;
    }

    @Override
    public List<BaseMenuDto> selectFirstMenuDtoList() {
        return null;
    }
}
