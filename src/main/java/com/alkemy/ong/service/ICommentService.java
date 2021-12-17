package com.alkemy.ong.service;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.dto.NewsResponse;
import com.alkemy.ong.model.Comment;

public interface ICommentService {

    Comment addComment(CommentRequest commentRequest);
    CommentResponse updateCommentsById(Long id, CommentRequest commentRequest, String authorizationHeader);
    }
