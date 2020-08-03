package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.service.ProductService;
import com.jitlantis.backend.API.dao.ProductDao;
import com.jitlantis.backend.API.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
}
