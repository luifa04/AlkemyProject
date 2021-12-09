
package com.alkemy.ong.controller;

import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.INewsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {
    
    @Autowired
    private INewsService newsService;
    
    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> delete(@Valid @PathVariable("id") Long id ) {
       
    	return newsService.delete(id);
		
    }
}
