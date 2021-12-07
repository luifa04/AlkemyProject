
package com.alkemy.ong.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import org.springframework.lang.Nullable;


@Getter
@Setter
public class CategoryRequestUpdate {
    
    @NotNull(message = "Name should be valid.", regexp = "^[A-Za-z\\s]+$")
    private String name;
    @Nullable
    private String description;
    @Nullable
    private String image;
}
