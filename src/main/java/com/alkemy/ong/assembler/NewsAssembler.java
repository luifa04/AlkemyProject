package com.alkemy.ong.assembler;

import com.alkemy.ong.controller.NewsController;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.News;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NewsAssembler extends RepresentationModelAssemblerSupport<News, NewsResponse> {

    public NewsAssembler(){
        super(NewsController.class, NewsResponse.class);
    }

    @Override
    public NewsResponse toModel(News news){

        NewsResponse newsResponse = instantiateModel(news);

        newsResponse.add(linkTo(
                    methodOn(NewsController.class)
                            .findById(news.getId()))
                            .withSelfRel());

        newsResponse.setId(news.getId());
        newsResponse.setName(news.getName());
        newsResponse.setContent(news.getContent());
        newsResponse.setImage(news.getImage());
        newsResponse.setCategoryId(news.getCategory().getId());

        return newsResponse;
    }

}
