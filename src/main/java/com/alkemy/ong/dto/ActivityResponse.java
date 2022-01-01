package com.alkemy.ong.dto;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    @NotBlank(message = "Name field is necessary")
    private String name;
    @NotBlank(message = "Content field is necessary")
    private String content;
    @URL(message = "Image field must be a valid url")
    private String image;
    private String dateCreation;
    private String dateUpdate;


}
