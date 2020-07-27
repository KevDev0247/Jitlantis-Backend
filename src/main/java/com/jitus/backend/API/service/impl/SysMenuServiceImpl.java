package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.SysMenuDao;
import com.jitus.backend.API.dto.BaseMenuDto;
import com.jitus.backend.API.model.SysMenu;
import com.jitus.backend.API.service.SysMenuService;
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
