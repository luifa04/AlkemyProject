package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public NewsResponse findById(Long id){
        String newsNotFound = messageSource.getMessage("news.notFound", null, Locale.US);
        News news = newsRepository.findById(id).orElseThrow(()-> new NotFoundException(newsNotFound));


        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setId(news.getId());
        newsResponse.setName(news.getName());
        newsResponse.setContent(news.getContent());
        newsResponse.setImage(news.getImage());
        newsResponse.setCategoryId(news.getCategory().getId());

        return newsResponse;

    }
}
