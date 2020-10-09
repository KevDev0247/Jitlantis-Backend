package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.Repair;

import java.util.List;

public interface RepairService extends IService<Repair> {

    Page<Repair> selectPageList(int pageNum, int pageSize, Integer status, String search);

    List<Repair> selall();

    List<Repair> getSearchList(String info);
}
