package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SlideResponse {
	
	  	@NotNull(message="the Url of the image cannot be empty")
		@URL(message = "Image field must be a valid url")
	    private String imageUrl;
	    
	    @NotNull(message="El text cannot be empty")
	    private String text;
	    
	    @NotNull(message="the order cannot be empty")
	    private Integer orderSlide;
	    
	    @NotNull(message="the organizationId cannot be empty")
	    private Long organizationId;
	    
	
}
