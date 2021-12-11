package com.alkemy.ong.dto;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActivityRequest {
	
	@Nullable
    private String name;
	@Nullable
    private String content;
	@Nullable
    private String image;
  
}
