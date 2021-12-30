package com.alkemy.ong.integration.user;

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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserListGeneralTest extends BaseUserTest {
    private final String PATH = "/users/";

    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {
        login(RoleEnum.USER.getRoleName());
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ListUserSuccess() {
        List<User> users = generateUsers(2);
        when(userRepository.findAll()).thenReturn(users);

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<List> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.GET,
                new HttpEntity<>(headers),
               List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
