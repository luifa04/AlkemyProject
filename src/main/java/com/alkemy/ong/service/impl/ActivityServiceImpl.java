package com.alkemy.ong.service.impl;

import java.time.LocalDateTime;
import java.util.Locale;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IActivityService;
import com.alkemy.ong.util.UpdateFields;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements IActivityService {
	
	 @Autowired
	 private ActivityRepository activityRepository;
	 private final UpdateFields updateFields;
	 private final MessageSource messageSource;
	 private static boolean hasUpdate = Boolean.FALSE;
	
	 
	 	@Override
	    @Transactional
		public ActivityResponse updateActivity(ActivityRequest activityRequest, Long id) {
		 
	 			String notFoundActivityMessage = messageSource.getMessage("activity.notFound", null, Locale.US);
		 
		 		Activity activity = activityRepository.findById(id)
                 .orElseThrow(()-> new NotFoundException(notFoundActivityMessage));
		 		
		 		updateFields.updateIfNotBlankAndNotEqual(activityRequest.getName(), activity.getName(), activity::setName , "name");
		 		updateFields.updateIfNotBlankAndNotEqual(activityRequest.getContent(), activity.getContent(), activity::setContent , "content");
		 		updateFields.updateIfNotBlankAndNotEqual(activityRequest.getImage(), activity.getImage(), activity::setImage , "image");
		 		
		 		if (hasUpdate){
		            activity.setDateUpdate(LocalDateTime.now());
		        }
		 		
		 	   return new ActivityResponse(activity.getName()
		 			   					   ,activity.getContent()
		 			   					   ,activity.getImage());
                       	
		}
	    
	 
}
