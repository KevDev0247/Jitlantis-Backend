package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.ClientProduct;
import org.mapstruct.Mapper;

@Mapper
public interface ClientProductDao extends BaseMapper<ClientProduct> {
}
