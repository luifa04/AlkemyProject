package com.alkemy.ong.dto;

import com.alkemy.ong.util.ImageExtension;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
public class TestimonialRequest {

    @NotNull(message = "name cannot be null")
    private String name;
    @URL(message = "image must be an URL")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;
    @NotNull(message = "content cannot be null")
    private String content;
}
