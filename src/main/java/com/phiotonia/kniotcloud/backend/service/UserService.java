package com.phiotonia.kniotcloud.backend.service;

import com.baomidou.mybatisplus.service.IService;
import com.phiotonia.kniotcloud.backend.model.User;

import java.util.List;

public interface UserService extends IService<User> {

    User findUserByName(String name);

    List<User> selectQueryList(String name, String email);

    boolean delete(Integer userId);
}
