package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.model.SysLog;

import java.util.List;

public interface SysLogService extends IService<SysLog> {
    List<SysLog> selectLogList(String fromTime, String toTime);
}
