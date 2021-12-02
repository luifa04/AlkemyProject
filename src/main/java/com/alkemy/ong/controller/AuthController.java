package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserRequest;
import com.alkemy.ong.exception.EmailExistException;
import com.alkemy.ong.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult) throws EmailExistException {
        if (bindingResult.hasErrors()) {
            modelAndview.addobject("error",bindingResult.getFieldErrors());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>((userService.createUser(userRequest)), HttpStatus.CREATED);

    }

}
