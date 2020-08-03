package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ProjectDao;
import com.jitlantis.backend.API.model.Project;
import com.jitlantis.backend.API.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {
}
