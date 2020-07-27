package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.Staff;
import org.mapstruct.Mapper;

@Mapper
public interface StaffDao extends BaseMapper<Staff> {
}
