package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.StaffDao;
import com.phiotonia.kniotcloud.backend.model.Staff;
import com.phiotonia.kniotcloud.backend.service.StaffService;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends ServiceImpl<StaffDao, Staff> implements StaffService {
}
