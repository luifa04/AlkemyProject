package com.alkemy.ong.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {

    private Long id;
    private String name;
    private String content;
    private String image;
    private Long categoryId;

}
