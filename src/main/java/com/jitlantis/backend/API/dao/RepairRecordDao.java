package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.RepairRecord;
import org.mapstruct.Mapper;

@Mapper
public interface RepairRecordDao extends BaseMapper<RepairRecord> {
}
