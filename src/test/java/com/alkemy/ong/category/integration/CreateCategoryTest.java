package com.alkemy.ong.category.integration;

import com.alkemy.ong.common.BaseGeneralTest;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateCategoryTest extends BaseGeneralTest {

    private final String PATH = "/category";

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        /*ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(,headers), Object.class);*/
        //assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }
}