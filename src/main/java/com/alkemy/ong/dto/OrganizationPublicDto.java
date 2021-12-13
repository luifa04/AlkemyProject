package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter

public class OrganizationPublicDto {

    private String name;
    private String image;
    private String address;
    private Long phone;
    private List<SlidePublicDto> slidePublicDto;

    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

}
