package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SysAttachmentsDao;
import com.jitlantis.backend.API.model.SysAttachments;
import com.jitlantis.backend.API.service.SysAttachmentsService;
import org.springframework.stereotype.Service;

@Service
public class SysAttachmentsServiceImpl extends ServiceImpl<SysAttachmentsDao, SysAttachments> implements SysAttachmentsService {
}
