package com.alkemy.ong.security;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.jwt.JwtProviderImpl;
import com.alkemy.ong.service.UserServiceImpl;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired(required=true)
    UserServiceImpl userServiceImpl;
    
    @Autowired
    JwtProviderImpl jwtProviderImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userServiceImpl.findByEmail(email).get();
        
        return (UserDetails) usuario;
    }
}
