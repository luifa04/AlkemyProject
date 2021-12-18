package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponseList;
import com.alkemy.ong.exception.EmptyDataException;
import com.alkemy.ong.mapper.CommentsMapper;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

    private final CommentRepository commentRepository;
    private final CommentsMapper commentsMapper;
    private final UserServiceImpl userService;
    private final NewsServiceImpl newsService;
    private final MessageSource messageSource;

    @Override
    public Comment addComment(CommentRequest commentRequest) {

        Comment comment = new Comment();
        User user = userService.findById(commentRequest.getUserId());
        News news = newsService.findByIdReturnNews(commentRequest.getNewsId());

        comment.setUser(user);
        comment.setNews(news);
        comment.setBody(commentRequest.getBody());

        return commentRepository.save(comment);

    }

    public List<CommentResponseList> getAll() {
        String commentListIsEmpty = messageSource.getMessage("comments.listEmpty", null, Locale.US);
        if(commentRepository.findAll().isEmpty()){
            throw new EmptyDataException(commentListIsEmpty);
        }
        List<Comment> entities = commentRepository.findAll();
        List<CommentResponseList> result = commentsMapper.commentModelList2DTOList(entities);
        return result;
    }
}
