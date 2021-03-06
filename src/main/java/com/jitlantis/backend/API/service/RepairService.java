package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.RepairStatusCountDto;
import com.jitlantis.backend.API.model.Repair;
import io.swagger.models.auth.In;

import java.util.List;

public interface RepairService extends IService<Repair> {

    Page<Repair> selectPageList(int pageNum, int pageSize, Integer status, String search);

    List<Repair> selall();

    List<Repair> getSearchList(String info);

    List<RepairStatusCountDto> getRepairCountByStatus();

    boolean updateStatus(Repair repair, Integer status);
}
