package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.service.SysRoleService;
import com.jitlantis.backend.API.dao.SysRoleDao;
import com.jitlantis.backend.API.model.SysRole;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> selectQueryList(String roleName, String remark) {
        EntityWrapper<SysRole> wrapper = new EntityWrapper<>();

        if (StringUtils.isNotBlank(roleName)) {
            wrapper.like("roleName", roleName);
        }
        if (StringUtils.isNotBlank(remark)) {
            wrapper.like("remark", remark);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);
        return this.selectList(wrapper);
    }
}
