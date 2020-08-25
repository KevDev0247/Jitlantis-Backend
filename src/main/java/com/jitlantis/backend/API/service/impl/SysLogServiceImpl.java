package com.jitlantis.backend.API.service.impl;

import com.jitlantis.backend.API.dao.SysLogDao;
import com.jitlantis.backend.API.model.SysLog;
import com.jitlantis.backend.API.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl {

    @Autowired
    private SysLogDao sysLogDao;

    public List<SysLog> selectLogList(String fromTime, String toTime) {
        if (!StringUtils.isNotBlank(fromTime)) {
            fromTime = null;
        }
        if (!StringUtils.isNotBlank(toTime)) {
            toTime = null;
        }

        List<SysLog> sysLogList = sysLogDao.selectLogList(fromTime, toTime);
        return sysLogList;
    }
}
