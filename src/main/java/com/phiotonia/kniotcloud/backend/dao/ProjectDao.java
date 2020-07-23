package com.phiotonia.kniotcloud.backend.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.phiotonia.kniotcloud.backend.model.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectDao extends BaseMapper<Project> {
}
