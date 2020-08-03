package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.jitlantis.backend.API.dao.SysUserDao;
import com.jitlantis.backend.API.service.SysUserService;
import com.jitlantis.backend.API.model.SysUser;
import com.jitlantis.backend.API.utils.DeletedEnum;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    private SysUserDao sysUserDao;

    @Override
    public SysUser findUserByName(String name) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        return this.selectOne(wrapper.eq("name", name));
    }

    @Override
    public List<SysUser> selectQueryList(String name, String email) {
        EntityWrapper<SysUser> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(email)) {
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
}
