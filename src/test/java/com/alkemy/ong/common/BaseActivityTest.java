package com.alkemy.ong.common;

import com.alkemy.ong.dto.ActivityRequest;

public class BaseActivityTest extends BaseGeneralTest {

    protected ActivityRequest exampleActivityRequest() {
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setName("Nueva Actividad");
        activityRequest.setImage("https://somosmas.jpg");
        activityRequest.setContent("Esta es una actividad para la familia");
        return activityRequest;
    }

}