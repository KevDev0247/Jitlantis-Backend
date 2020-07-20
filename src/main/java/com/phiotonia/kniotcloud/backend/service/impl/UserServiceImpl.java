package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.phiotonia.kniotcloud.backend.dao.UserDao;
import com.phiotonia.kniotcloud.backend.model.SysUser;
import com.phiotonia.kniotcloud.backend.service.UserService;
import com.phiotonia.kniotcloud.backend.utils.DeletedEnum;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, SysUser> implements UserService {

    private UserDao userDao;

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
