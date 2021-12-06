package com.alkemy.ong.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.security.RoleEnum;
import com.alkemy.ong.service.IUserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/users")
	@PreAuthorize("hasAnyRole(T(com.alkemy.ong.security.RoleEnum).ADMIN)")
    public ResponseEntity<?> getUsers(){
        try{
            List<UserDto> list = userService.getUsers();
            return new ResponseEntity<>((UserDto) list, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser (@Valid @PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto result = userService.update(id, userDto);
        return new ResponseEntity<UserDto>(result, HttpStatus.OK);
    }
	
}
