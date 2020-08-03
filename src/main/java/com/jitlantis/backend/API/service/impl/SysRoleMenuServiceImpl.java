package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysRoleMenuDao;
import com.jitlantis.backend.API.service.SysRoleMenuService;
import com.jitlantis.backend.API.model.SysRoleMenu;
import org.springframework.stereotype.Service;

@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> implements SysRoleMenuService {
}
