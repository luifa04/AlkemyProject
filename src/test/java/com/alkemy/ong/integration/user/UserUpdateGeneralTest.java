package com.alkemy.ong.integration.user;

import com.alkemy.ong.dto.UserUpdateDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserUpdateGeneralTest extends BaseUserTest {
    private final Long ID2DELETE =generateUser(RoleEnum.USER.getRoleName()).getUserId();
    private final String PATH = "/users/" + ID2DELETE;
    private UserUpdateDto userUpdateDto = new UserUpdateDto();

    @Before
    public void setUp(){
        userUpdateDto=exampleUserRequest();
    }

    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PATCH, new HttpEntity<>(userUpdateDto, headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(userRepository.findById(eq(ID2DELETE))).thenReturn(Optional.empty());

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PATCH, new HttpEntity<>(userUpdateDto,headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void UpdateUserSuccess() {
        User userModified = generateUser(RoleEnum.USER.getRoleName());
        userModified.setUserId(1L);
        userModified.setFirstName("Modified");
        userModified.setLastName("Modified");
        userModified.setPassword("123456");
        userModified.setEmail("modified@hotmail.com");
        userModified.setPhoto("https://modified.jpg");


        when(userRepository.findById(eq(ID2DELETE)))
                .thenReturn(Optional.of(generateUser(RoleEnum.USER.getRoleName())));

        when(userRepository.save(any(User.class)))

                .thenReturn(userModified);

        login(RoleEnum.ADMIN.getRoleName());

        userUpdateDto.setFirstName("Modified");
        userUpdateDto.setLastName("Modified");
        userUpdateDto.setEmail("modified@hotmail.com");
        userUpdateDto.setPhoto("https://modified.jpg");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.PATCH, new HttpEntity<>(userUpdateDto, headers), UserUpdateDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
