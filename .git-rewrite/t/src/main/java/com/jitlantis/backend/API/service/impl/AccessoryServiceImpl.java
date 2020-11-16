package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.AccessoryDao;
import com.jitlantis.backend.API.model.Accessory;
import com.jitlantis.backend.API.service.AccessoryService;
import org.springframework.stereotype.Service;

@Service
public class AccessoryServiceImpl extends ServiceImpl<AccessoryDao, Accessory> implements AccessoryService {
}
