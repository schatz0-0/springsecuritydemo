package com.zime.consumerclient.service.impl;

import com.zime.consumerclient.mapper.JwtUserMapper;
import com.zime.consumerclient.pojo.JwtUser;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserServiceImpl implements UserDetailsService {

    private JwtUserMapper jwtUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Lazy
    public JwtUserServiceImpl(JwtUserMapper jwtUserMapper, PasswordEncoder passwordEncoder) {
        this.jwtUserMapper = jwtUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JwtUser user = jwtUserMapper.loadUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("账户不存在");
        }
        user.setRoles(jwtUserMapper.getJwtUserRolesByUid(user.getUserId()));
        return user;
    }
}
