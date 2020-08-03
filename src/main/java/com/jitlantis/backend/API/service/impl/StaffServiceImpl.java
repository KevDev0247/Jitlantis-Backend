package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.StaffDao;
import com.jitlantis.backend.API.model.Staff;
import com.jitlantis.backend.API.service.StaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffDao, Staff> implements StaffService {
}
