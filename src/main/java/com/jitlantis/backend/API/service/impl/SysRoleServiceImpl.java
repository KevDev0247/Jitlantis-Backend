package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.service.SysRoleService;
import com.jitlantis.backend.API.dao.SysRoleDao;
import com.jitlantis.backend.API.model.SysRole;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
}
