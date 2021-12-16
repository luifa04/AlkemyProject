package com.alkemy.ong.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.alkemy.ong.util.ImageExtension;
import org.hibernate.validator.constraints.URL;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialResponse extends RepresentationModel<TestimonialResponse> {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @URL(message = "image must be an URL")
    @ImageExtension(message = "image extension not valid, must be JPG, JPEG or PNG")
    private String image;
    @NotBlank(message = "content cannot be blank")
    private String content;

}
