package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.SysUserRole;

import java.util.List;

public interface SysUserRoleService extends IService<SysUserRole> {

    List<SysUserRole> getRolesByUserName(String userId);
}
