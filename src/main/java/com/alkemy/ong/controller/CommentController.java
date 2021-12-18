package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentResponseList;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {


    private final ICommentService commentService;

    @GetMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<List<CommentResponseList>> getAll() {
        List<CommentResponseList> comments = commentService.getAll();
        return ResponseEntity.ok().body(comments);
    }
}
