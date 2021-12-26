package com.alkemy.ong.common;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.security.RoleEnum;

import java.util.ArrayList;
import java.util.List;

public class BaseActivityTest extends BaseGeneralTest {

    protected ActivityRequest exampleActivityRequest() {
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setName("Nueva Actividad");
        activityRequest.setImage("https://somosmas.jpg");
        activityRequest.setContent("Esta es una actividad para la familia");
        return activityRequest;
    }

    protected List<Activity> createActivity(int count) {
        List<Activity> activities = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            activities.add(generateActivity(RoleEnum.ADMIN.getRoleName()));
        }
        return activities;
    }

}