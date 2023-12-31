package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationResponse {
    private String name;
    private String image;
    private String address;
    private Long phone;
    private String email;
    private String welcomeText;
    private String aboutUsText;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;

}
