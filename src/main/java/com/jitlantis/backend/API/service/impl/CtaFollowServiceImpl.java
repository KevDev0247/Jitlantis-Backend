package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.CtaFollowDao;
import com.jitlantis.backend.API.model.CtaFollow;
import com.jitlantis.backend.API.service.CtaFollowService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for CtaFollow
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/27
 */
@Service
public class CtaFollowServiceImpl extends ServiceImpl<CtaFollowDao, CtaFollow> implements CtaFollowService {
}
