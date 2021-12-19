package com.alkemy.ong.service;

import org.springframework.http.ResponseEntity;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.model.Comment;

public interface ICommentService {

    Comment addComment(CommentRequest commentRequest);

	ResponseEntity<?> delete(Long id);

}
