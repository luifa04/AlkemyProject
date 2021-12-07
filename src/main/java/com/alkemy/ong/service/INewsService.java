package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsResponse;

public interface INewsService{

    NewsResponse findById(Long id);
}
