package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ProDocumentDao;
import com.jitlantis.backend.API.model.ProDocument;
import com.jitlantis.backend.API.service.ProDocumentService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for ProDocument
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/27
 */
@Service
public class ProDocumentServiceImpl extends ServiceImpl<ProDocumentDao, ProDocument> implements ProDocumentService {
}
