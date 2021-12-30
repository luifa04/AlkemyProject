package com.alkemy.ong.integration.user;

import com.alkemy.ong.common.BaseGeneralTest;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserDeleteGeneralTest extends BaseGeneralTest {
    private final Long ID2DELETE =generateUser(RoleEnum.USER.getRoleName()).getUserId();
    private final String PATH = "/users/" + ID2DELETE;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Before
    public void AdminLogin() {
        login(RoleEnum.ADMIN.getRoleName());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(userRepository.findById(eq(ID2DELETE))).thenReturn(Optional.empty());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void DeleteUserSuccess() {
        when(userRepository.findById(eq(ID2DELETE)))
                .thenReturn(Optional.of(generateUser(RoleEnum.USER.getRoleName())));


        when(userRepository.save(eq(generateUser(RoleEnum.USER.getRoleName()))))
                .thenReturn(generateUser(RoleEnum.USER.getRoleName()));

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
