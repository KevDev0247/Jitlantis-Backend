package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.SysUser;

import java.util.List;

/**
 * The Service interface for System Users.
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * created on 2020/07/21
 */
public interface SysUserService extends IService<SysUser> {

    SysUser findUserByName(String name);

    List<SysUser> selectQueryList(String name, String email);

    List<SysUser> selectClientQueryList(String name, String email);

    List<SysUser> selectRepairmanQueryList(String name, String company);

    List<BaseItemDto> optionList(String name);

    boolean delete(Integer userId);
}
