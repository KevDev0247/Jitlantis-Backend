package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ProFollowDao;
import com.jitlantis.backend.API.model.ProFollow;
import com.jitlantis.backend.API.service.ProFollowService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for ProFollow
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/27
 */
@Service
public class ProFollowServiceImpl extends ServiceImpl<ProFollowDao, ProFollow> implements ProFollowService {
}
