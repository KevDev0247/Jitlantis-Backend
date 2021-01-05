package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ProjectDao;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.model.Project;
import com.jitlantis.backend.API.service.ProjectService;
import com.jitlantis.backend.API.utils.DeletedEnum;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementation of the Service interface for Project
 *
 * @author Kevin Zhijun Wang, Yonggang Su
 * created on 2020/07/27
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {

    @Autowired
    ProjectDao projectDao;

    @Override
    public Project selectById(Integer id) {
        return projectDao.selectById(id);
    }

    @Override
    public List<Project> queryList(String name) {
        EntityWrapper<Project> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", name);
        }
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);
        return this.selectList(wrapper);
    }

    @Override
    public List<Project> findAllByIds(List<Long> projectIds) {
        EntityWrapper<Project> wrapper = new EntityWrapper<>();
        wrapper.in("id", projectIds);
        wrapper.eq("is_delete", DeletedEnum.N.value());
        wrapper.orderBy("id", true);

        return this.selectList(wrapper);
    }
}
