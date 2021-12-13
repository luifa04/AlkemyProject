package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SlidePublicDto {

    private Long id;
    private String imageUrl;
    private String text;
    private Integer orderSlide;
    private Long organizationId;

}
