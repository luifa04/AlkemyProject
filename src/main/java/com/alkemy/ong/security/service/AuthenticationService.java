package com.alkemy.ong.security.service;

import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.LoginDTO;

public interface AuthenticationService {
	
	public User signInAndReturnJWT(LoginDTO signInRequest);
}
