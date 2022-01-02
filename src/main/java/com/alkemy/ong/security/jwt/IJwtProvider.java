package com.alkemy.ong.security.jwt;

import org.springframework.security.core.Authentication;

import com.alkemy.ong.security.service.UserDetailsImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IJwtProvider {
    String generateToken(UserDetailsImpl auth);

    Authentication getAuthentication(HttpServletRequest request) ;

    boolean validateToken(HttpServletRequest request) ;
}
