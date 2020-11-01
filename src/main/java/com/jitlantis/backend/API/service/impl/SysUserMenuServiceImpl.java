package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysUserMenuDao;
import com.jitlantis.backend.API.dto.MainMenuDto;
import com.jitlantis.backend.API.model.SysUserMenu;
import com.jitlantis.backend.API.service.SysUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserMenuServiceImpl extends ServiceImpl<SysUserMenuDao, SysUserMenu> implements SysUserMenuService {

    @Autowired
    private SysUserMenuDao sysUserMenuDao;

    @Override
    public List<MainMenuDto> getMainMenus(Integer userId, Integer isShow) {
        return sysUserMenuDao.getMainMenus(userId, isShow);
    }
}
