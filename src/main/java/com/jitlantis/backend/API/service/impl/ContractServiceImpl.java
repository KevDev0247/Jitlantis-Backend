package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ContractDao;
import com.jitlantis.backend.API.model.Contract;
import com.jitlantis.backend.API.service.ContractService;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl extends ServiceImpl<ContractDao, Contract> implements ContractService {
}
