package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsResponse newsModelToDTO(News news) {
        NewsResponse newsResponse = new NewsResponse();

        newsResponse.setId(news.getId());
        newsResponse.setName(news.getName());
        newsResponse.setContent(news.getContent());
        newsResponse.setImage(news.getImage());
        newsResponse.setCategoryId(news.getCategory().getId());

        return newsResponse;
    }
}
