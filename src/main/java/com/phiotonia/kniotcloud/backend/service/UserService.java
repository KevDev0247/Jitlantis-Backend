package com.phiotonia.kniotcloud.backend.service;

import com.baomidou.mybatisplus.service.IService;
import com.phiotonia.kniotcloud.backend.model.SysUser;

import java.util.List;

public interface UserService extends IService<SysUser> {

    SysUser findUserByName(String name);

    List<SysUser> selectQueryList(String name, String email);

    boolean delete(Integer userId);
}
