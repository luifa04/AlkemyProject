package com.alkemy.ong.integration.activity;


import com.alkemy.ong.common.BaseActivityTest;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;

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

    @BeforeEach
    void createActivity(){

        Activity activity = generateActivity();
        Mockito.when(activityRepository.save(isA(Activity.class))).thenReturn(generateActivity());
    }

    @Before
    public void login(){
        login(RoleEnum.ADMIN.getRoleName());
    }

    @Test
    public void CreateActivitySuccess() {

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("New Activity");
        activityRequest.setImage("https://activity.jpg");
        activityRequest.setContent("New Content");

        Mockito.when(activityRepository.save(isA(Activity.class))).thenReturn(generateActivity());

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/activity"), HttpMethod.POST, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void CreateActivityFailedBecauseImage() {

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("New Activity");
        activityRequest.setImage("image");
        activityRequest.setContent("New Content");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/activity"), HttpMethod.POST, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    public void CreateActivityFailedBecauseName() {

        ActivityRequest activityRequest = exampleActivityRequest();
        activityRequest.setName("");
        activityRequest.setImage("https://activity.jpg");
        activityRequest.setContent("New Content");

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort("/activity"), HttpMethod.POST, new HttpEntity<>(activityRequest, headers), ActivityRequest.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}
