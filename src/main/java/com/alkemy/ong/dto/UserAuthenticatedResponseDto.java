package com.alkemy.ong.dto;

import com.alkemy.ong.util.docs.AuthenticationConstantDocs;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthenticatedResponseDto {

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_ID)
    private Long id;
    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_FIRSTNAME)
    private String firstName;
    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_LASTNAME)
    private String lastName;
    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_EMAIL)
    private String email;
    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_PHOTO)
    private String photo;
}
