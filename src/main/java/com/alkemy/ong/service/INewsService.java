package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsRequest;
import com.alkemy.ong.dto.NewsResponse;

public interface INewsService {
    NewsResponse updateNewsById(Long id, NewsRequest news);
}
