package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.SysAttachmentsDao;
import com.jitus.backend.API.model.SysAttachments;
import com.jitus.backend.API.service.SysAttachmentsService;
import org.springframework.stereotype.Service;

@Service
public class SysAttachmentsServiceImpl extends ServiceImpl<SysAttachmentsDao, SysAttachments> implements SysAttachmentsService {
}
