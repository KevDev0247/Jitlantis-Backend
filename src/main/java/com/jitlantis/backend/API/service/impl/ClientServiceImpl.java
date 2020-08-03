package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ClientDao;
import com.jitlantis.backend.API.model.Client;
import com.jitlantis.backend.API.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements ClientService {
}
