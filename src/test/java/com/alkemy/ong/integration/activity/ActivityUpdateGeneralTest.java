package com.alkemy.ong.integration.activity;


import com.alkemy.ong.common.BaseActivityTest;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.security.RoleEnum;
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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityUpdateGeneralTest extends BaseActivityTest {
    private final Long ID = generateActivity(RoleEnum.USER.getRoleName()).getId();
    private final String PATH = "/activity/" + ID;

    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {
        login(RoleEnum.USER.getRoleName());
        ActivityRequest activityRequest = exampleActivityRequest();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(activityRequest, headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        Activity activityMod = generateActivity(RoleEnum.ADMIN.getRoleName());
        Long ID = 1500L;
        login(RoleEnum.ADMIN.getRoleName());
        ActivityRequest userUpdateDto = exampleActivityRequest();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort("/activity/" + ID),
                HttpMethod.PUT, new HttpEntity<>(userUpdateDto,headers), Object.class);

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    @Test
    public void UpdateActivitySuccess() {
        Activity activityMod = generateActivity(RoleEnum.ADMIN.getRoleName());
        activityMod.setId(1L);
        activityMod.setName("Modified");
        activityMod.setImage("https://modified.jpg");
        activityMod.setContent("Content Modify");

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("Modified");
        activityRequest.setImage("https://modified.jpg");
        activityRequest.setContent("Content Modify");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.PUT, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void UpdateActivityFailedBecauseName() {
        Activity activityMod = generateActivity(RoleEnum.ADMIN.getRoleName());
        activityMod.setId(1L);
        activityMod.setName("");
        activityMod.setImage("https://modified.jpg");
        activityMod.setContent("Content Modify");

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("");
        activityRequest.setImage("https://modified.jpg");
        activityRequest.setContent("Content Modify");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.PUT, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void UpdateActivityFailedBecauseImage() {
        Activity activityMod = generateActivity(RoleEnum.ADMIN.getRoleName());
        activityMod.setId(1L);
        activityMod.setName("Modified");
        activityMod.setImage("image");
        activityMod.setContent("Content Modify");

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();
        activityMod.setName("Modified");
        activityRequest.setImage("image");
        activityRequest.setContent("Content Modify");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.PUT, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}