package com.alkemy.ong.security.jwt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.alkemy.ong.security.UserDetailsImpl;


public interface IJwtProvider {
    String generateToken(UserDetailsImpl auth);

    Authentication getAuthentication(HttpServletRequest request);

    boolean validateToken(HttpServletRequest request);

	String generateToken(UserDetails userDetails);


}
