package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.ClientProductDao;
import com.jitus.backend.API.model.ClientProduct;
import com.jitus.backend.API.service.ClientProductService;
import org.springframework.stereotype.Service;

@Service
public class ClientProductImpl extends ServiceImpl<ClientProductDao, ClientProduct> implements ClientProductService {
}
