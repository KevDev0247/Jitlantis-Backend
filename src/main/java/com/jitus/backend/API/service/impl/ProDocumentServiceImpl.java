package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.ProDocumentDao;
import com.jitus.backend.API.model.ProDocument;
import com.jitus.backend.API.service.ProDocumentService;
import org.springframework.stereotype.Service;

@Service
public class ProDocumentServiceImpl extends ServiceImpl<ProDocumentDao, ProDocument> implements ProDocumentService {
}
