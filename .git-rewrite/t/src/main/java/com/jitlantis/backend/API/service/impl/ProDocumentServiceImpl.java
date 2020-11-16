package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ProDocumentDao;
import com.jitlantis.backend.API.model.ProDocument;
import com.jitlantis.backend.API.service.ProDocumentService;
import org.springframework.stereotype.Service;

@Service
public class ProDocumentServiceImpl extends ServiceImpl<ProDocumentDao, ProDocument> implements ProDocumentService {
}
