package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.InstallProductDao;
import com.jitlantis.backend.API.model.InstallProduct;
import com.jitlantis.backend.API.service.InstallProductService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for InstallProduct
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/29
 */
@Service
public class InstallProductServiceImpl extends ServiceImpl<InstallProductDao, InstallProduct> implements InstallProductService {
}
