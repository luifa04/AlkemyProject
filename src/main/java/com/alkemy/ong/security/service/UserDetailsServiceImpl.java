
package com.alkemy.ong.security.service;

import javax.transaction.Transactional;
import com.alkemy.ong.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alkemy.ong.model.User;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


  
    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userServiceImpl.findByEmail(email);
        return (UserDetails) usuario;
    }
}
