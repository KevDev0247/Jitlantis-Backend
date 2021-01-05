package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.AccessoryDao;
import com.jitlantis.backend.API.model.Accessory;
import com.jitlantis.backend.API.service.AccessoryService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for Accessory
 *
 * @author Kevin Zhijun Wang
 * created on 2020/09/10
 */
@Service
public class AccessoryServiceImpl extends ServiceImpl<AccessoryDao, Accessory> implements AccessoryService {
}
