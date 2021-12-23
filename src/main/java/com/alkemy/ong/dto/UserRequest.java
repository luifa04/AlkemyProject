package com.alkemy.ong.dto;

import com.alkemy.ong.util.docs.AuthenticationConstantDocs;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_USERREQUEST_FIRSTNAME)
    @NotBlank(message = "Name is necessary.")
    private String firstName;

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_USERREQUEST_LASTNAME)
    @NotBlank(message = "Last name is necessary.")
    private String lastName;

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_USERREQUEST_EMAIL)
    @NotBlank(message = "Email is necessary.")
    @Email(message = "Email should be valid.", regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_USERREQUEST_PASS)
    @NotBlank(message = "Password is necessary.")
    @Length(min = 5, message = "Password must be min 5  characters.")
    private String password;

    @ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_USERREQUEST_PHOTO)
    @Nullable
    private String photo;

}
