package com.jitus.backend.API.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jitus.backend.API.dao.ContactDao;
import com.jitus.backend.API.model.Contact;
import com.jitus.backend.API.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends ServiceImpl<ContactDao, Contact> implements ContactService {
}
