package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.ProductDao;
import com.jitus.backend.API.model.Product;
import com.jitus.backend.API.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
}
