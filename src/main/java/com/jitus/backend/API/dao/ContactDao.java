package com.jitus.backend.API.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jitus.backend.API.model.Contact;
import org.mapstruct.Mapper;

@Mapper
public interface ContactDao extends BaseMapper<Contact> {
}
