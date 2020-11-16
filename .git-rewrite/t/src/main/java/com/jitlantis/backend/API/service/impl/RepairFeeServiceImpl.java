package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.RepairFeeDao;
import com.jitlantis.backend.API.model.RepairFee;
import com.jitlantis.backend.API.service.RepairFeeService;
import org.springframework.stereotype.Service;

@Service
public class RepairFeeServiceImpl extends ServiceImpl<RepairFeeDao, RepairFee> implements RepairFeeService {
}
