package com.alkemy.ong.service;

import org.springframework.http.ResponseEntity;



public interface ICommentService {

 
	ResponseEntity<?> delete(Long id);

}
