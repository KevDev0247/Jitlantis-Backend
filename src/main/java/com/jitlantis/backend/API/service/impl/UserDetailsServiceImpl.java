package com.jitlantis.backend.API.service.impl;

import com.jitlantis.backend.API.service.SysUserService;
import com.jitlantis.backend.API.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.findUserByName(userId);
        if (sysUser == null) {
            throw new UsernameNotFoundException(userId);
        }
        return new org.springframework.security.core.userdetails.
                User(sysUser.getName(), sysUser.getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }
}
