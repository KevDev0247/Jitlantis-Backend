package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDao extends BaseMapper<Product> {
}
