package com.alkemy.ong.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse extends RepresentationModel<NewsResponse> {

    private Long id;
    private String name;
    private String content;
    private String image;
    private Long categoryId;

}
