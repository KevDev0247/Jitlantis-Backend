package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysAttachmentsDao;
import com.jitlantis.backend.API.model.SysAttachments;
import com.jitlantis.backend.API.service.SysAttachmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysAttachmentsServiceImpl extends ServiceImpl<SysAttachmentsDao, SysAttachments> implements SysAttachmentsService {

    @Autowired
    private SysAttachmentsDao sysAttachmentsDao;

    @Override
    public int create(SysAttachments sysAttachments) {
        return sysAttachmentsDao.createAttachments(sysAttachments);
    }
}
