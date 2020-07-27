package com.jitus.backend.API.service;

import com.jitus.backend.API.model.SysUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public SysUser login(String name, String password) {
        LOGGER.info(">>>> start enter Login>>>>");
        SysUser sysUser = sysUserService.findUserByName(name);
        if (sysUser != null) {
            boolean match = encoder.matches(password, sysUser.getPassword());
            if (match) {
                return sysUser;
            }
        }
        return null;
    }

    public SysUser loadUserById(String userId) {
        return sysUserService.findUserByName(userId);
    }
}
