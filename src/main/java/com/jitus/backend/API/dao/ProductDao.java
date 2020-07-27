package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDao extends BaseMapper<Product> {
}
