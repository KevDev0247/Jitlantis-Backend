package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SendProductDao;
import com.jitlantis.backend.API.model.SendProduct;
import com.jitlantis.backend.API.service.SendProductService;
import org.springframework.stereotype.Service;

@Service
public class SendProductServiceImpl extends ServiceImpl<SendProductDao, SendProduct> implements SendProductService {
}
