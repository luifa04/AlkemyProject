package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class NewsRequest {

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "content cannot be null")
    private String content;

    @NotNull(message = "image cannot be null")
    @URL(message = "url format not valid")
    private String image;

    @NotNull(message = "categoryId cannot be null")
    private Long categoryId;

}
