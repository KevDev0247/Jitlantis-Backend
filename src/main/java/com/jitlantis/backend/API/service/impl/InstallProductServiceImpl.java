package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.InstallProductDao;
import com.jitlantis.backend.API.model.InstallProduct;
import com.jitlantis.backend.API.service.InstallProductService;
import org.springframework.stereotype.Service;

@Service
public class InstallProductServiceImpl extends ServiceImpl<InstallProductDao, InstallProduct> implements InstallProductService {
}
