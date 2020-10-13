package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.SysMessage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMessageDao extends BaseMapper<SysMessage> {

}
