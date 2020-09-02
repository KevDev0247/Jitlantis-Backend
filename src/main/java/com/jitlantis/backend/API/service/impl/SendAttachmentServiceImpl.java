package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.SendAttachmentDao;
import com.jitlantis.backend.API.model.SendAttachment;
import com.jitlantis.backend.API.service.SendAttachmentService;
import org.springframework.stereotype.Service;

@Service
public class SendAttachmentServiceImpl extends ServiceImpl<SendAttachmentDao, SendAttachment> implements SendAttachmentService {
}
