package com.jitus.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitus.backend.API.model.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {

    SysUser findUserByName(String name);

    List<SysUser> selectQueryList(String name, String email);

    boolean delete(Integer userId);
}
