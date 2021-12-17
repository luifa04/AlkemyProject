package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.openapitools.jackson.nullable.JsonNullable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    @NotNull(message = "name cannot be null")
    private String name;
    @URL(message = "Must be a valid URL")
    private JsonNullable<String> facebookUrl;
    @URL(message = "Must be a valid URL")
    private JsonNullable<String> instagramUrl;
    @URL(message = "Must be a valid URL")
    private JsonNullable<String> linkedinUrl;
    @NotNull(message = "image cannot be null")
    @URL(message = "url format not valid")
    private String image;
    private String description;

}
