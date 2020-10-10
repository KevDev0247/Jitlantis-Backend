package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.Product;
import com.jitlantis.backend.API.model.Project;

import java.util.List;

public interface ProjectService extends IService<Project> {

    Project selectById(Integer id);

    List<Project> queryList(String name);

    List<Project> findAllByIds(List<Long> projectIds);
}
