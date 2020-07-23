package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.ProDocumentDao;
import com.phiotonia.kniotcloud.backend.model.ProDocument;
import com.phiotonia.kniotcloud.backend.service.ProDocumentService;
import org.springframework.stereotype.Service;

@Service
public class ProDocumentServiceImpl extends ServiceImpl<ProDocumentDao, ProDocument> implements ProDocumentService {
}
