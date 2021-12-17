
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentRequest;

import com.alkemy.ong.service.ICommentService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {
    
    private final ICommentService commentService;
    
    @PostMapping
    public ResponseEntity<CommentRequest> createNews(@Valid @RequestBody CommentRequest comment) throws Exception {
        CommentRequest comentRequest = commentService.addComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(comentRequest);
    }

}
