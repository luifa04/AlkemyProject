package com.alkemy.ong.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private IActivityService activityService;
	
	 	@PutMapping("/{id}")
	 	@PreAuthorize(SecurityConstant.ADMIN)
	    public ResponseEntity<ActivityResponse> update(@Valid @RequestBody ActivityRequest activity, @PathVariable("id") Long id){
	        return new ResponseEntity<ActivityResponse>(activityService.updateActivity(activity, id), HttpStatus.OK);
	    }

}
