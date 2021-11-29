/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.controller;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mateo
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
     
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id ) {
        try {
			Category category = categoryService.findById(id);
                          return new ResponseEntity<>(category, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
		}
    }
    
    
}