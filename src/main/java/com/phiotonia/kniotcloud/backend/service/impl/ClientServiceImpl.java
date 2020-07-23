package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.ClientDao;
import com.phiotonia.kniotcloud.backend.model.Client;
import com.phiotonia.kniotcloud.backend.service.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements ClientService {
}
