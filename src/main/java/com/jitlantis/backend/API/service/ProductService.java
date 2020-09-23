package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Product;

import java.util.List;

public interface ProductService extends IService<Product> {

    List<BaseItemDto> optionList(String name);

    Product selectById(Integer id);
}
