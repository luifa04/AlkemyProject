
package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface INewsService {

    NewsRequest createNews(NewsRequest news) throws Exception;

    NewsResponse findById(Long id);

    NewsResponse updateNewsById(Long id, NewsRequest news);

    ResponseEntity<?> delete(Long id);

    News findByIdReturnNews(Long id);

    Page<News> findAll(Pageable pageable, int page);
}
