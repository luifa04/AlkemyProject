package com.alkemy.ong.controller;

import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICommentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
	
	private final ICommentService commentService;
	 
	@DeleteMapping("/{id}")
	@PreAuthorize(SecurityConstant.USER_ADMIN) 
	public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id) {

		return commentService.delete(id);

	}
	
	
}
