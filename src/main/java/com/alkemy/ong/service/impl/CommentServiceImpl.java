package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

	private final CommentRepository commentRepository;
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

	@Override
    public ResponseEntity<?> delete(Long id) {
		User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String notFoundCommentMessage = messageSource.getMessage("comment.notFound", null, Locale.US);
    	String isDeletedCommentMessage = messageSource.getMessage("comment.isDeleted", null, Locale.US); 
    	String accessDeniedCommentMessage = messageSource.getMessage("comment.accessDenied", null, Locale.US);
    	
    	Comment comment = commentRepository.findById(id)
                 .orElseThrow(()-> new NotFoundException(notFoundCommentMessage)); 
    	
    	if(comment.getUser().getUserId() == activeUser.getUserId()) {
    		commentRepository.delete(comment);
    		return new ResponseEntity<>(isDeletedCommentMessage, HttpStatus.OK);           
    	} else {
    		throw new AccessDeniedException(accessDeniedCommentMessage);
    	}
    	        
    }
}
