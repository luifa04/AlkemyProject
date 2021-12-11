package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactRequestDto {
    @NotBlank(message="The name field is required")
    @NotEmpty(message = "The name field cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @Nullable
    private String phone;

    @NotBlank(message="The email field is required")
    @NotEmpty(message = "The email field cannot be empty")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid.", regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
    private String email;

    @NotBlank(message="The message is required")
    @NotEmpty(message = "The message field cannot be empty")
    @NotNull(message = "Message cannot be null")
    private String message;

}
