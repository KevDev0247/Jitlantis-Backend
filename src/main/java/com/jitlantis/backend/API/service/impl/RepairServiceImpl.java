package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.RepairDao;
import com.jitlantis.backend.API.model.Repair;
import com.jitlantis.backend.API.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairServiceImpl extends ServiceImpl<RepairDao, Repair> implements RepairService {

    @Autowired
    private RepairDao repairDao;

    @Override
    public Page<Repair> selectPageList(int pageNum, int pageSize, Integer status, String search) {
        Page<Repair> page = new Page<>(pageNum, pageSize);
        page.setRecords(repairDao.selectPageList(page, status, search));
        return page;
    }
}
