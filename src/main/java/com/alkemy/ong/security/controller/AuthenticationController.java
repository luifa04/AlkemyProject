package com.alkemy.ong.security.controller;

import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.service.CustomUserDetailsService;
import com.alkemy.ong.security.service.IAuthenticationService;
import com.alkemy.ong.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    private final IUserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserRequest user) throws EmailExistException{
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginDto user){
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUserDetails(@RequestHeader(value = "Authorization") String authorizationHeader){
        return new ResponseEntity<>(customUserDetailsService.getUserDetails(authorizationHeader),HttpStatus.OK);
    }

}
