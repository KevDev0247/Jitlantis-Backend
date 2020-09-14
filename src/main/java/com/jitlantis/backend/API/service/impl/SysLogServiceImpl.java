package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysLogDao;
import com.jitlantis.backend.API.model.SysLog;
import com.jitlantis.backend.API.service.SysLogService;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogDao, SysLog> implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    public List<SysLog> selectLogList(String fromTime, String toTime) {
        if (!StringUtils.isNotBlank(fromTime)) {
            fromTime = null;
        }
        if (!StringUtils.isNotBlank(toTime)) {
            toTime = null;
        }

        return sysLogDao.selectLogList(fromTime, toTime);
    }
}
