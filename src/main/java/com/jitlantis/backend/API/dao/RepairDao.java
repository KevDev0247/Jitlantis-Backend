package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jitlantis.backend.API.model.Repair;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RepairDao extends BaseMapper<Repair> {

    List<Repair> selectPageList(Page<Repair> page, @Param("status") Integer status, @Param("search") String search);
}
