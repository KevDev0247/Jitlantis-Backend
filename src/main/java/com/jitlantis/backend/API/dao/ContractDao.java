package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.Contract;
import org.mapstruct.Mapper;

@Mapper
public interface ContractDao extends BaseMapper<Contract> {
}
