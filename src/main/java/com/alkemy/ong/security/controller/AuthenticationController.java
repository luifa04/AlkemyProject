package com.alkemy.ong.security.controller;

import com.alkemy.ong.util.docs.AuthenticationConstantDocs;
import com.alkemy.ong.dto.UserAuthenticatedResponseDto;
import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.security.dto.LoggedUserDto;
import com.alkemy.ong.security.dto.LoginDto;
import com.alkemy.ong.security.service.CustomUserDetailsService;
import com.alkemy.ong.security.service.IAuthenticationService;
import com.alkemy.ong.service.IUserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Api(value = AuthenticationConstantDocs.AUTHENTICATION)
public class AuthenticationController {

    private final IAuthenticationService authenticationService;
    private final IUserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    @ApiOperation(value = AuthenticationConstantDocs.AUTHENTICATION_SINGUP,
            notes = AuthenticationConstantDocs.AUTHENTICATION_SINGUP_NOTE,
            response = LoggedUserDto.class)
    @PostMapping("/register")
    public ResponseEntity<?> signUp(@ApiParam(value = AuthenticationConstantDocs.AUTHENTICATION_SINGUP_PARAM, required = true)
                                        @Valid @RequestBody UserRequest user) throws EmailExistException{
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
    @ApiOperation(value = AuthenticationConstantDocs.AUTHENTICATION_SINGIN,
            notes = AuthenticationConstantDocs.AUTHENTICATION_SINGIN_NOTE,
            response = LoggedUserDto.class)
    @PostMapping("/login")
    public ResponseEntity<?> signIn(@ApiParam(value = AuthenticationConstantDocs.AUTHENTICATION_SINGIN_PARAM, required = true)
                                        @Valid @RequestBody LoginDto user){
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }

    @ApiOperation(value = AuthenticationConstantDocs.AUTHENTICATION_ME,
            notes = AuthenticationConstantDocs.AUTHENTICATION_ME_NOTE,
            response = UserAuthenticatedResponseDto.class)
    @GetMapping("/me")
    public ResponseEntity<?> getAuthenticatedUserDetails(@ApiParam(value = AuthenticationConstantDocs.AUTHENTICATION_ME_PARAM, required = true)
                                                             @RequestHeader(value = "Authorization") String authorizationHeader){
        return new ResponseEntity<>(customUserDetailsService.getUserDetails(authorizationHeader),HttpStatus.OK);
    }

}
