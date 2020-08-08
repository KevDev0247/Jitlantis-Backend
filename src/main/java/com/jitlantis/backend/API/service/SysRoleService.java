package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.SysRole;

import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    List<SysRole> selectQueryList(String roleName, String remark);
}
