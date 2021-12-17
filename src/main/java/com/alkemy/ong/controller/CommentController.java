package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentRequest;
import com.alkemy.ong.dto.CommentResponse;
import com.alkemy.ong.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private final ICommentService commentService;

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateCommentsById(@Valid @RequestBody CommentRequest comment, @PathVariable("id") Long id, @RequestHeader(value = "Authorization") String authorizationHeader){
        return new ResponseEntity<>(commentService.updateCommentsById(id,comment,authorizationHeader), HttpStatus.OK);
    }
}
