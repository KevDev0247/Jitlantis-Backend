package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.SysLog;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SysLogDao extends BaseMapper<SysLog> {
    List<SysLog> selectLogList(@Param("fromTime") String fromTime, @Param("toTime") String toTime);
}
