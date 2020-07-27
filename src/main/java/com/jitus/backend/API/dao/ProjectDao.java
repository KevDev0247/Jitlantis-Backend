package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.Project;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectDao extends BaseMapper<Project> {
}
