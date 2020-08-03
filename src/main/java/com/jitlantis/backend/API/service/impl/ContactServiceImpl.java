package com.jitlantis.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitlantis.backend.API.dao.ContactDao;
import com.jitlantis.backend.API.model.Contact;
import com.jitlantis.backend.API.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends ServiceImpl<ContactDao, Contact> implements ContactService {
}
