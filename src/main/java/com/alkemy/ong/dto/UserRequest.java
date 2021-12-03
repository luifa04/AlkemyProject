package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Name is necessary.")
    private String firstName;

    @NotBlank(message = "Last name is necessary.")
    private String lastName;

    @NotBlank(message = "Email is necessary.")
    @Email(message = "Email should be valid.", regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;

    @NotBlank(message = "Password is necessary.")
    @Size(min = 5, max = 15, message = "Password must be between 5 and 15 characters.")
    private String password;

    @Nullable
    private String photo;

}
