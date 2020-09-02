package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.RepairDao;
import com.jitlantis.backend.API.model.Repair;
import com.jitlantis.backend.API.service.RepairService;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl extends ServiceImpl<RepairDao, Repair> implements RepairService {
}
