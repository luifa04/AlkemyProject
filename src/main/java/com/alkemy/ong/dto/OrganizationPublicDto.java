package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class OrganizationPublicDto {

    private String name;
    private String image;
    private String address;
    private Long phone;
    private List<SlidePublicDto> slidePublicDto;

}
