package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.EvaluateDao;
import com.jitlantis.backend.API.model.Evaluate;
import com.jitlantis.backend.API.service.EvaluateService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for Evaluate
 *
 * @author Kevin Zhijun Wang
 * created on 2020/08/29
 */
@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateDao, Evaluate> implements EvaluateService {
}
