package com.alkemy.ong.dto;

import com.alkemy.ong.util.docs.NewsConstantsDocs;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse extends RepresentationModel<NewsResponse> {

    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTORESPONSE_ID)
    private Long id;
    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTORESPONSE_NAME)
    private String name;
    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTORESPONSE_CONTENT)
    private String content;
    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTORESPONSE_IMAGE)
    private String image;
    @ApiModelProperty(value = NewsConstantsDocs.NEWS_DTORESPONSE_CATEGORY)
    private Long categoryId;

}
