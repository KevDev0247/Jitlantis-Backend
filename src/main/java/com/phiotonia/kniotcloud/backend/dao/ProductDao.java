package com.phiotonia.kniotcloud.backend.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.phiotonia.kniotcloud.backend.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDao extends BaseMapper<Product> {
}
