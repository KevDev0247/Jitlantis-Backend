package com.phiotonia.kniotcloud.backend.service.impl;

import com.phiotonia.kniotcloud.backend.model.User;
import com.phiotonia.kniotcloud.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userService.findUserByName(userId);
        if (user == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new org.springframework.security.core.userdetails.
                User(user.getName(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}
