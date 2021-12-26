package com.alkemy.ong.integration.user;

import com.alkemy.ong.common.BaseGeneralTest;
import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.RoleEnum;

import java.util.ArrayList;
import java.util.List;

public class BaseUserTest extends BaseGeneralTest {

    protected UserUpdateDto exampleUserRequest() {
        UserUpdateDto userUpdateRequest = new UserUpdateDto();
        userUpdateRequest.setFirstName("David");
        userUpdateRequest.setLastName("Marcos");
        userUpdateRequest.setEmail("david.marcos@hotmail.com.com");
        userUpdateRequest.setPhoto("https://somosmas.jpg");
        return userUpdateRequest;
    }

    protected List<User> generateUsers(int count){
        List<User> users = new ArrayList<>();
        for(int i=0; i<=count;i++){
            users.add(generateUser(RoleEnum.USER.getRoleName()));
        }
        return users;
    }
}

