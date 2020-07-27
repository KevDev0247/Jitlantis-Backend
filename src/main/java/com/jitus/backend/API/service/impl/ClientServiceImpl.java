package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.ClientDao;
import com.jitus.backend.API.model.Client;
import com.jitus.backend.API.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements ClientService {
}
