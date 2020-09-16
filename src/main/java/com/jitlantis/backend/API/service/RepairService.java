package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.Repair;

public interface RepairService extends IService<Repair> {

    Page<Repair> selectPageList(int pageNum, int pageSize, Integer status, String search);
}
