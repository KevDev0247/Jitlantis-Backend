package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.ClientProduct;
import org.mapstruct.Mapper;

@Mapper
public interface ClientProductDao extends BaseMapper<ClientProduct> {
}
