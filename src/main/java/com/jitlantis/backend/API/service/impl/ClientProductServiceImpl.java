package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ClientProductDao;
import com.jitlantis.backend.API.model.ClientProduct;
import com.jitlantis.backend.API.service.ClientProductService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for ClientProduct
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/24
 */
@Service
public class ClientProductServiceImpl extends ServiceImpl<ClientProductDao, ClientProduct> implements ClientProductService {
}
