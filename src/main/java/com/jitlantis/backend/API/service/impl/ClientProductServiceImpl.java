package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ClientProductDao;
import com.jitlantis.backend.API.model.ClientProduct;
import com.jitlantis.backend.API.service.ClientProductService;
import org.springframework.stereotype.Service;

@Service
public class ClientProductServiceImpl extends ServiceImpl<ClientProductDao, ClientProduct> implements ClientProductService {
}
