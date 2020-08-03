package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientDao extends BaseMapper<Client> {
}
