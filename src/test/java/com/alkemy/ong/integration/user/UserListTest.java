package com.alkemy.ong.integration.user;

import com.alkemy.ong.common.BaseTest;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserListTest extends BaseTest {
    private final String PATH = "/users/";

    private List<UserDto> users;

    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {
        login(RoleEnum.USER.getRoleName());
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void ListUserSuccess() {
        List<User> users = generateUsers(2);
        when(userRepository.findAll()).thenReturn(users);

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity List<UserDto> response = testRestTemplate.exchange(
                createURLWithPort(PATH),
                HttpMethod.GET, new HttpEntity<>(headers), List<UserDto>);

        User user = users.get(0);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getUsers());
        assertEquals(user.getId(), response.getBody().getUsers().get(0).getId());
        assertEquals(user.getFirstName(), response.getBody().getUsers().get(0).getFirstName());
        assertEquals(user.getLastName(), response.getBody().getUsers().get(0).getLastName());
        assertEquals(user.getEmail(), response.getBody().getUsers().get(0).getEmail());
        assertEquals(user.getPhoto(), response.getBody().getUsers().get(0).getPhoto());
    }



}
