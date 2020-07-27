package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.StaffDao;
import com.jitus.backend.API.model.Staff;
import com.jitus.backend.API.service.StaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffDao, Staff> implements StaffService {
}
