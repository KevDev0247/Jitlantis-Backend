package com.phiotonia.kniotcloud.backend.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.phiotonia.kniotcloud.backend.model.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientDao extends BaseMapper<Client> {
}
