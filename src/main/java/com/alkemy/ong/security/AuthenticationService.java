package com.alkemy.ong.security;

import com.alkemy.ong.model.User;

public interface AuthenticationService {
	
	public User signInAndReturnJWT(LoginDTO signInRequest);
}
