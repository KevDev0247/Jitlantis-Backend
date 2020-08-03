package com.jitlantis.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitlantis.backend.API.model.Contact;
import org.mapstruct.Mapper;

@Mapper
public interface ContactDao extends BaseMapper<Contact> {
}
