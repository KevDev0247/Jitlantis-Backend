package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.CtaFollowDao;
import com.jitus.backend.API.model.CtaFollow;
import com.jitus.backend.API.service.CtaFollowService;
import org.springframework.stereotype.Service;

@Service
public class CtaFollowServiceImpl extends ServiceImpl<CtaFollowDao, CtaFollow> implements CtaFollowService {
}
