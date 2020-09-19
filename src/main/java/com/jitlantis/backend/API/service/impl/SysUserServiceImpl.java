package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.base.JitConverter;
import com.jitlantis.backend.API.dao.SysUserDao;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.service.SysUserService;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the Service interface for system user
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * created on 2020/07/21
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private JitConverter jitConverter;

    private SysUserDao sysUserDao;

    @Override
    public SysUser findUserByName(String name) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        return this.selectOne(wrapper.eq("name", name));
    }

    @Override
    public List<SysUser> selectQueryList(String name, String email) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(email)) {
            wrapper.like("name", name);
            wrapper.like("email", email);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);
        return this.selectList(wrapper);
    }

    @Override
    public boolean delete(Integer userId) {
        SysUser sysUser = this.selectById(userId);
        boolean res = false;
        if (sysUser != null) {
            sysUser.setIsDelete(DeletedEnum.Y.value());
            sysUser.setUpdateTime(new Date());
            res = this.updateById(sysUser);
        }
        return res;
    }

    @Override
    public List<SysUser> selectClientQueryList(String name, String email) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        if (StringUtils.isNotBlank(email)) {
            wrapper.like("email", email);
        }
        List<Integer> roleIds = new ArrayList<>();
        roleIds.add(7);
        roleIds.add(8);
        roleIds.add(9);
        wrapper.in("role_id", roleIds);
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);
        return this.selectList(wrapper);
    }

    @Override
    public List<BaseItemDto> optionList(String name) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id");
        List<SysUser> userList = this.selectList(wrapper);

        List<BaseItemDto> baseItemDtoList = jitConverter.mergeListByAny(BaseItemDto.class, userList, null, null);
        if (baseItemDtoList == null || baseItemDtoList.size() == 0) {
            baseItemDtoList = new ArrayList<>();
        }

        return baseItemDtoList;
    }
}
