
package com.alkemy.ong.service;


import org.springframework.http.ResponseEntity;


public interface INewsService {
    
    ResponseEntity<?> delete(Long id);
}
