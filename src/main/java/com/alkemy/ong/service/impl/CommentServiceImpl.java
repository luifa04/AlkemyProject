package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import org.springframework.context.MessageSource;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;

    @Override
    public CommentRequest addComment(CommentRequest commentRequestDto) {
        Comment comment = commentDTOEntity(commentRequestDto);
        Comment commentSave = commentRepository.save(comment);
        CommentRequest result = commentEntity2DTO(commentSave);

        return result;

    }
    
    public Comment commentDTOEntity(CommentRequest commentDto){
        Comment comment= new Comment();
        User user = userService.findById(commentDto.getUserId());
        News news = newsService.findByIdReturnNews(commentDto.getNewsId());
        
        comment.setNews(news);
        comment.setUser(user);
        comment.setBody(commentDto.getBody());
        comment.setDateUpdate(LocalDateTime.now());
        return comment;
    }
    
    public CommentRequest commentEntity2DTO(Comment comment){
        CommentRequest commentDto= new CommentRequest();
        
        commentDto.setBody(comment.getBody());
        commentDto.setUserId(comment.getUser().getUserId());
        commentDto.setNewsId(comment.getNews().getId());

        return commentDto;
    }
}
