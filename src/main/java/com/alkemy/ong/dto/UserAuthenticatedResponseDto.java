package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticatedResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String photo;
}
