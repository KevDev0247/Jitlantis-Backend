package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.ProFollowDao;
import com.jitus.backend.API.model.ProFollow;
import com.jitus.backend.API.service.ProFollowService;
import org.springframework.stereotype.Service;

@Service
public class ProFollowServiceImpl extends ServiceImpl<ProFollowDao, ProFollow> implements ProFollowService {
}
