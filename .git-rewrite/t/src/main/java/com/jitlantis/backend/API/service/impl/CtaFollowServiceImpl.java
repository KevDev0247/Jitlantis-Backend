package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.CtaFollowDao;
import com.jitlantis.backend.API.model.CtaFollow;
import com.jitlantis.backend.API.service.CtaFollowService;
import org.springframework.stereotype.Service;

@Service
public class CtaFollowServiceImpl extends ServiceImpl<CtaFollowDao, CtaFollow> implements CtaFollowService {
}
