package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/users")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<UserDto>> getUsers(){
            List<UserDto> list = userService.getUsers();
            return new ResponseEntity<List<UserDto>>(list, HttpStatus.OK);
    }


    @PatchMapping("/users/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<UserUpdateDto> updateUser (@Valid @PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto) {
        UserUpdateDto result = userService.update(id, userUpdateDto);
        return new ResponseEntity<UserUpdateDto>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id ) {

        return userService.deleteById(id);

    }

}
