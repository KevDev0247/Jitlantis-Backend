package com.phiotonia.kniotcloud.backend.service;

import com.phiotonia.kniotcloud.backend.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    public User login(String name, String password) {
        LOGGER.info(">>>> start enter Login>>>>");
        User user = userService.findUserByName(name);
        if (user != null) {
            boolean match = encoder.matches(password, user.getPassword());
            if (match) {
                return user;
            }
        }
        return null;
    }

    public User loadUserById(String userId) {
        return userService.findUserByName(userId);
    }
}
