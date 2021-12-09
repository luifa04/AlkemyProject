
package com.alkemy.ong.service.impl;


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
public class NewsServiceImpl implements INewsService{
    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ResponseEntity<?> delete(Long id) {
        String newsNotFound = messageSource.getMessage("news.notFound", null, Locale.US);
        String isDeletedNewsMessage = messageSource.getMessage("news.isDeleted", null, Locale.US);
        
        News news = newsRepository.findById(id).orElseThrow(()-> new NotFoundException(newsNotFound));
        newsRepository.delete(news);
        return new ResponseEntity<>(isDeletedNewsMessage, HttpStatus.OK);
    }
    
    
}
