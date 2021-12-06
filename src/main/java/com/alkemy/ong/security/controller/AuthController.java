package com.alkemy.ong.security.controller;


import javax.validation.Valid;

import com.alkemy.ong.dto.UserAuthenticatedResponseDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.dto.LoginDTO;
import com.alkemy.ong.security.service.AuthenticationServiceImpl;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private AuthenticationServiceImpl authenticationServiceImpl;


	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO login, BindingResult bindingResult) {

		try {
			if (bindingResult.hasErrors())
				return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

			return new ResponseEntity<User>(authenticationServiceImpl.signInAndReturnJWT(login), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>("{ok = false }" + "error: " + e.getLocalizedMessage(), HttpStatus.CONFLICT);
		}

	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) throws EmailExistException, MethodArgumentNotValidException {
			if(bindingResult.hasErrors()) throw new MethodArgumentNotValidException(null, bindingResult);
			return new ResponseEntity<>((userServiceImpl.createUser(userRequest)), HttpStatus.CREATED);
	}

	@GetMapping(value = "/me")
	public ResponseEntity<UserAuthenticatedResponseDto> getAuthenticatedUserDetails(
			@RequestHeader(value = "Authorization") String authorizationHeader) {
		return new ResponseEntity<>(userServiceImpl.getUserDetails(authorizationHeader), HttpStatus.OK);
	}

}
