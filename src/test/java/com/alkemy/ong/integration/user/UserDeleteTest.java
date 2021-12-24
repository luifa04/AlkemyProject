package com.alkemy.ong.integration.user;

import com.alkemy.ong.common.BaseTest;
import com.alkemy.ong.security.RoleEnum;
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
public class UserDeleteTest extends BaseTest {
    private final Long ID2DELETE =generateUser(RoleEnum.USER.getRoleName()).getUserId();
    private final String PATH = "/users/" + ID2DELETE;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(userRepository.findById(eq(ID2DELETE))).thenReturn(Optional.empty());

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void DeleteUserSuccess() {
        when(userRepository.findById(eq(ID2DELETE)))
                .thenReturn(Optional.of(generateUser(RoleEnum.USER.getRoleName())));


        when(userRepository.save(eq(generateUser(RoleEnum.USER.getRoleName()))))
                .thenReturn(generateUser(RoleEnum.USER.getRoleName()));

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
