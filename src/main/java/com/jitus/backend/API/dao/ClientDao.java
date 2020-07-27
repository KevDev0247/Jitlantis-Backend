package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientDao extends BaseMapper<Client> {
}
