package com.alkemy.ong.controller;

import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private INewsService newsService;

    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> findById(@Valid @PathVariable("id") Long id){

        return new ResponseEntity<>(newsService.findById(id), HttpStatus.OK);
    }
}
