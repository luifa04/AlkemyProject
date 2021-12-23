package com.alkemy.ong.security.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.alkemy.ong.controller.docs.AuthenticationConstantDocs;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_EMAIL)
    @NotBlank
    @NotNull(message= "You must enter your email to be able to login")
    private String email;
    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_PASS)
    @NotBlank
    @NotNull(message= "You must enter your password to be able to login")
    private String password;

}