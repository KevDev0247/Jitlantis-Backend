package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.Staff;
import org.mapstruct.Mapper;

@Mapper
public interface StaffDao extends BaseMapper<Staff> {
}
