package com.phiotonia.kniotcloud.backend.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.phiotonia.kniotcloud.backend.model.Contact;
import org.mapstruct.Mapper;

@Mapper
public interface ContactDao extends BaseMapper<Contact> {
}
