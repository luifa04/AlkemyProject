package com.alkemy.ong.service;

import javax.validation.Valid;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;

public interface IActivityService {

	ActivityResponse updateActivity(@Valid ActivityRequest activityRequest, Long id);

}
