package com.phiotonia.kniotcloud.backend.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.phiotonia.kniotcloud.backend.dao.ContactDao;
import com.phiotonia.kniotcloud.backend.model.Contact;
import com.phiotonia.kniotcloud.backend.service.ContactService;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl extends ServiceImpl<ContactDao, Contact> implements ContactService {
}
