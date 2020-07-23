package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.ProjectDao;
import com.phiotonia.kniotcloud.backend.model.Project;
import com.phiotonia.kniotcloud.backend.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, Project> implements ProjectService {
}
