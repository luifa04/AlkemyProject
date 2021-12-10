package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
public class TestimonialRequest {

    @NotBlank(message = "name cannot be blank")
    private String name;
    @URL(message = "image must be an URL")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;
    @NotBlank(message = "content cannot be blank")
    private String content;
}
