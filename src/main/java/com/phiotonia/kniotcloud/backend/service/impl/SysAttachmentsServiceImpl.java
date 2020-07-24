package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.SysAttachmentsDao;
import com.phiotonia.kniotcloud.backend.model.SysAttachments;
import com.phiotonia.kniotcloud.backend.service.SysAttachmentsService;
import org.springframework.stereotype.Service;

@Service
public class SysAttachmentsServiceImpl extends ServiceImpl<SysAttachmentsDao, SysAttachments> implements SysAttachmentsService {
}
