package com.phiotonia.kniotcloud.backend.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.phiotonia.kniotcloud.backend.model.User;
import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;

@Mapper
public interface UserDao extends BaseMapper<User> {

}
