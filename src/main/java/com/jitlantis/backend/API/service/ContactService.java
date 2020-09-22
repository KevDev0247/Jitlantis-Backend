package com.jitlantis.backend.API.service;

import com.baomidou.mybatisplus.service.IService;
import com.jitlantis.backend.API.dto.BaseItemDto;
import com.jitlantis.backend.API.model.Contact;

import java.util.List;

public interface ContactService extends IService<Contact> {

    List<BaseItemDto> optionList(String name);
}
