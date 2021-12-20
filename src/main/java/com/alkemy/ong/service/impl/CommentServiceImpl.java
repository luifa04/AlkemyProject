package com.alkemy.ong.service.impl;


import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Comment;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.CommentRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.ICommentService;
import lombok.RequiredArgsConstructor;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements ICommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final UserServiceImpl userService;
	private final NewsServiceImpl newsService;
	private final MessageSource messageSource;

	
	@Override
    public ResponseEntity<?> delete(Long id) {
		UserDetails activeUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = activeUser.getUsername();
		User user = userRepository.findByEmail(username).get();
		
    	String notFoundCommentMessage = messageSource.getMessage("comment.notFound", null, Locale.US);
    	String isDeletedCommentMessage = messageSource.getMessage("comment.isDeleted", null, Locale.US); 
    	String notAllowedCommentMessage = messageSource.getMessage("comment.notAllowed", null, Locale.US);
    	
    	Comment comment = commentRepository.findById(id)
                 .orElseThrow(()-> new NotFoundException(notFoundCommentMessage)); 
    	
    	if(comment.getUser().getUserId() == user.getUserId()) {
    		commentRepository.delete(comment);
    		return new ResponseEntity<>(isDeletedCommentMessage, HttpStatus.OK);           
    	} else {
    		throw new BadCredentialsException(notAllowedCommentMessage);
    	}
    	        
    }


}
