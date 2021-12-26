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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityCreateGeneralTest extends BaseActivityTest {


    @Test
    public void ReturnUnauthorizedIfUserIsNotAdmin() {

        login(RoleEnum.USER.getRoleName());
        ActivityRequest activityRequest = exampleActivityRequest();
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort("/activity"),
                HttpMethod.POST, new HttpEntity<>(activityRequest, headers), Object.class);
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void CreateActivitySuccess() {
        Activity activity = generateActivity(RoleEnum.ADMIN.getRoleName());
        activity.setId(1L);
        activity.setName("New Activity");
        activity.setImage("https://activity.jpg");
        activity.setContent("New Content");

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("New Activity");
        activityRequest.setImage("https://activity.jpg");
        activityRequest.setContent("New Content");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/activity"), HttpMethod.POST, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void CreateActivityFailed() {
        Activity activity = generateActivity(RoleEnum.ADMIN.getRoleName());
        activity.setId(1L);
        activity.setName("New Activity");
        activity.setImage("image");
        activity.setContent("New Content");

        login(RoleEnum.ADMIN.getRoleName());

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("New Activity");
        activityRequest.setImage("image");
        activityRequest.setContent("New Content");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/activity"), HttpMethod.POST, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
