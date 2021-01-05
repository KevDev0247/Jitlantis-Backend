package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ClientDao;
import com.jitlantis.backend.API.model.Client;
import com.jitlantis.backend.API.service.ClientService;
import org.springframework.stereotype.Service;

/**
 * The implementation of the Service interface for Client
 *
 * @author Kevin Zhijun Wang
 * created on 2020/07/27
 */
@Service
public class ClientServiceImpl extends ServiceImpl<ClientDao, Client> implements ClientService {
}
